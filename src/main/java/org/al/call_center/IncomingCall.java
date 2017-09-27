package org.al.call_center;

import org.al.call_center.center.Person;

/**
 * Created by jose on 26/09/17.
 */
public class IncomingCall {

    Person personAssigned;

    public Person getPersonAssigned() {
        return personAssigned;
    }

    public void setPersonAssigned(Person person){
        personAssigned = person;
    }
}
