package radholm.scenario2.common;

public enum RoleType {
    EMPLOYEE("EMPLOYEE"), MANAGER("MANAGER"), CEO("CEO");

    private final String employee;

    RoleType(String employee) {
        this.employee = employee;
    }
}
