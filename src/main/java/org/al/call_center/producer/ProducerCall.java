package org.al.call_center.producer;

import org.al.call_center.IncomingCall;

import java.util.concurrent.BlockingQueue;

/**
 * Created by jose on 26/09/17.
 */
public class ProducerCall implements Runnable{

    BlockingQueue<IncomingCall> queue;

    public ProducerCall(BlockingQueue<IncomingCall> queue){
        this.queue = queue;
    }

    public void run() {
        queue.add(new IncomingCall());
    }
}
