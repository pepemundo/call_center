import org.al.call_center.IncomingCall;
import org.al.call_center.center.CallCenter;
import org.al.call_center.consumer.Dispatcher;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by jose on 26/09/17.
 */
public class DispatcherTest {

    ExecutorService executor;
    BlockingQueue<IncomingCall> queue = new LinkedBlockingQueue<IncomingCall>();

    @Before
    public void setUp(){
        executor = Executors.newFixedThreadPool(50);
       //CallCenter with 7 agents, 2 supervisors and 1 director
        executor.submit(new Dispatcher(new CallCenter(7,2,1),queue));
    }

    @After
    public void tearDown(){
        queue.clear();
        executor.shutdownNow();
    }
    @Test
    public void testDispatch_10_calls() throws ExecutionException, InterruptedException {
        List<Future<IncomingCall>> assigments = new ArrayList<Future<IncomingCall>>();

        for(int i = 0; i < 10; i++){
            assigments.add(executor.submit(new Producer()));
        }
        Thread.sleep(3000);
        //All the calls were assigned
        for(Future<IncomingCall> calls : assigments){
            Assert.assertNotNull(calls.get().getPersonAssigned());
        }
    }

    @Test
    public void testDispatch_11_calls() throws ExecutionException, InterruptedException {
        List<Future<IncomingCall>> assigments = new ArrayList<Future<IncomingCall>>();

        for(int i = 1; i <= 11; i++){
            assigments.add(executor.submit(new Producer()));
        }
        Thread.sleep(3000);
        //First 10 calls were assigned
        for(int i = 0; i < 10; i++){
            Assert.assertNotNull(assigments.get(i).get().getPersonAssigned());
        }
        //The 11 call was not assigned yet
        Assert.assertNull(assigments.get(10).get().getPersonAssigned());
        //waits just a bit
        Thread.sleep(5000);

        Assert.assertNotNull(assigments.get(10).get().getPersonAssigned());

    }

    private class Producer implements Callable<IncomingCall>{

        public IncomingCall call() throws Exception {
            IncomingCall call = new IncomingCall();
            queue.add(call);
            return call;
        }
    }
}
