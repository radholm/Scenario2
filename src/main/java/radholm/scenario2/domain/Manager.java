package radholm.scenario2.domain;

import java.util.List;

public class Manager extends Employee {

    private List<Employee> subordinates;
    private boolean isManager = true;

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }
}
