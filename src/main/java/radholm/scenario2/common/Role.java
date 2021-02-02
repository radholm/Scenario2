package radholm.scenario2.common;

public class Role {

    private final RoleType type;
    private final Double salaryCoefficient;

    public Role(RoleType type) {
        this.type = type;
        this.salaryCoefficient = salaryCoefficient(type);
    }

    private static Double salaryCoefficient(RoleType type) {
        return switch (type) {
            case EMPLOYEE -> 1.125;
            case MANAGER -> 1.725;
            case CEO -> 2.725;
        };
    }

    public RoleType getRole() {
        return type;
    }

    public Double getSalaryCoefficient() {
        return salaryCoefficient;
    }
}
