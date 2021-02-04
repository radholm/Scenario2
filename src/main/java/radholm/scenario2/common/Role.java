package radholm.scenario2.common;

import java.io.Serializable;

/**
 * Class to help define roles
 */
public class Role implements Serializable {

    private RoleType type;
    private Double salaryCoefficient;
    private Boolean isManager;
    private Boolean isCeo;

    public Role() {

    }

    /**
     * Initializes a specific role
     *
     * @param type the role to create
     */
    public Role(RoleType type) {
        this.type = type;
        this.salaryCoefficient = salaryCoefficient(type);
        this.isManager = isManager(type);
        this.isCeo = isCeo(type);
    }

    /**
     * Gets the salary coefficient for the specified role
     *
     * @param type the specified role
     * @return the salary coefficient
     */
    private static Double salaryCoefficient(RoleType type) {
        return switch (type) {
            case EMPLOYEE -> 1.125;
            case MANAGER -> 1.725;
            case CEO -> 2.725;
        };
    }

    /**
     * Fetches if a role is considered a manager role
     *
     * @param type the specified role
     * @return true if role is a manager role, false otherwise
     */
    private static Boolean isManager(RoleType type) {
        return switch (type) {
            case EMPLOYEE -> false;
            case MANAGER, CEO -> true;
        };
    }

    /**
     * Fetches if a role is considered a CEO role
     *
     * @param type the specified role
     * @return true if role is a CEO role, false otherwise
     */
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

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean getIsCeo() {
        return isCeo;
    }

    public void getIsManager(Boolean isCeo) {
        this.isCeo = isCeo;
    }
}
