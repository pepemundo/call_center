package org.al.call_center;

import org.al.call_center.center.CallCenter;
import org.al.call_center.consumer.Dispatcher;
import org.al.call_center.producer.ProducerCall;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jose on 26/09/17.
 */
public class Main {
    public static void main(String []args){
        ExecutorService service =  Executors.newFixedThreadPool(10);

        LinkedBlockingQueue<IncomingCall> queue = new LinkedBlockingQueue<IncomingCall>();
        service.submit(new Dispatcher(new CallCenter(1,1,1),queue));

        service.submit(new ProducerCall(queue));
        service.submit(new ProducerCall(queue));
        service.submit(new ProducerCall(queue));
        service.submit(new ProducerCall(queue));
        service.submit(new ProducerCall(queue));
    }
}
