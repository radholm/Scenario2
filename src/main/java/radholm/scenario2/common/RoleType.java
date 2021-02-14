package radholm.scenario2.common;

/**
 * Enum type to specify an employees role
 */
public enum RoleType {
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
    CEO("CEO");

    private final String employee;

    RoleType(String employee) {
        this.employee = employee;
    }
}
