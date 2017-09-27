package org.al.call_center.consumer;

import org.al.call_center.IncomingCall;
import org.al.call_center.center.AssignResponse;
import org.al.call_center.center.CallCenter;
import org.al.call_center.center.TakeIncomingCall;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jose on 26/09/17.
 *
 * Dispatcher es el encargado de consumir las llamadas entrantes. Bloquea hasta que una nueva llamada ingrese a la cola y trata de asignarla.
 * Espera hasta que algun operador este disponible en el callcenter
 *
 */
public class Dispatcher implements Runnable{

    private CallCenter callCenter;
    private ExecutorService executor = Executors.newFixedThreadPool(100);
    private BlockingQueue<IncomingCall> waitingCalls;

    public Dispatcher(CallCenter callcenter, BlockingQueue<IncomingCall> waitingCalls){
        this.callCenter = callcenter;
        this.waitingCalls = waitingCalls;
    }

    public void run(){
        try {
            while(true){
                IncomingCall newCall = waitingCalls.take();
                while(!dispatchCall(newCall)){
                    //Couldn't assign the call, wait for someone available in the callcenter
                    Thread.sleep(500);
                }
             }
        } catch (InterruptedException e) {
            executor.shutdownNow();
           //bye bye
        }
    }


    private boolean dispatchCall(IncomingCall call) {
        AssignResponse response = callCenter.assign(call);

        if(response.getAssigned() == null){
            System.out.println("coultn't assign no one available, wait");
            return false;
        }
        call.setPersonAssigned(response.getAssigned());
        System.out.println("Assigned to "+response.getAssigned().getRole());
        executor.submit(new TakeIncomingCall(callCenter, call));
        return true;
    }
}
