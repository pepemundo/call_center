package org.al.call_center.center;

/**
 * Created by jose on 26/09/17.
 */
public class AssignResponse {
    Person assigned;
    public AssignResponse(Person assigned) {
        this.assigned = assigned;
    }

    public AssignResponse() {
    }

    public Person getAssigned(){
        return assigned;
    }
}

