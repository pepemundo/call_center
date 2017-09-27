package org.al.call_center.center;

import org.al.call_center.IncomingCall;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jose on 26/09/17.
 */
public class CallCenter {

    private Queue<Person> agents = new ConcurrentLinkedQueue<Person>();
    private Queue<Person> supervisors = new ConcurrentLinkedQueue<Person>();
    private Queue<Person> directors = new ConcurrentLinkedQueue<Person>();

    public CallCenter(int agents, int supervisors, int directors){
        //initilize queues
        for(int i=1;i<=agents;i++){
            makeAvailable(new Person(ROLE.AGENT));
        }
        //initilize queues
        for(int i=1;i<=supervisors;i++){
            makeAvailable(new Person(ROLE.SUPERVISOR));
        }
        //initilize queues
        for(int i=1;i<=directors;i++) {
            makeAvailable(new Person(ROLE.DIRECTOR));
        }
    }


    public AssignResponse assign(IncomingCall call) {

        Person agent = agents.poll();
        if(agent != null){
            return new AssignResponse(agent);
        }
        Person supervisor = supervisors.poll();
        if(supervisor != null){
            return new AssignResponse(supervisor);
        }
        Person director = directors.poll();
        if(director != null){
            return new AssignResponse(director);
        }

        return new AssignResponse();
    }

    public void makeAvailable(Person person){
        System.out.println("I'm available " + person.getRole());
        if(person.getRole().equals(ROLE.AGENT)){
            agents.add(person);
        }

        if(person.getRole().equals(ROLE.SUPERVISOR)){
            supervisors.add(person);
        }

        if(person.getRole().equals(ROLE.DIRECTOR)){
            directors.add(person);
        }
    }
}
