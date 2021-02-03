package radholm.scenario2.common;

import java.io.Serializable;

public class Role implements Serializable {

    private RoleType type;
    private Double salaryCoefficient;
    private Boolean isManager;
    private Boolean isCeo;

    public Role() {

    }

    public Role(RoleType type) {
        this.type = type;
        this.salaryCoefficient = salaryCoefficient(type);
        this.isManager = isManager(type);
        this.isCeo = isCeo(type);
    }

    private static Double salaryCoefficient(RoleType type) {
        return switch (type) {
            case EMPLOYEE -> 1.125;
            case MANAGER -> 1.725;
            case CEO -> 2.725;
        };
    }

    private static Boolean isManager(RoleType type) {
        return switch (type) {
            case EMPLOYEE -> false;
            case MANAGER, CEO -> true;
        };
    }

    private static Boolean isCeo(RoleType type) {
        return switch (type) {
            case CEO -> true;
            case EMPLOYEE, MANAGER -> false;
        };
    }

    public RoleType getRoleType() {
        return type;
    }

    public Double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public Boolean getIsCeo() {
        return isCeo;
    }
}
