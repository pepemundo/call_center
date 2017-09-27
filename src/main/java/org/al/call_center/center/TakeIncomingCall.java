package org.al.call_center.center;

import org.al.call_center.IncomingCall;

/**
 * Created by jose on 26/09/17.
 */

public class TakeIncomingCall implements Runnable{

    CallCenter center;
    IncomingCall call;

    public TakeIncomingCall(CallCenter center, IncomingCall call){
        this.center = center;
        this.call = call;
    }

    public void run() {
        //talking

        try {
            Thread.sleep(5000);
            //hang and make me available again
            center.makeAvailable(call.getPersonAssigned());
        } catch (InterruptedException e) {
           //
        }


    }
}
