package radholm.scenario2.common;

public enum SalaryCoefficient {
    EMPLOYEE(1.125),
    MANAGER(1.725),
    CEO(2.725);

    public final double coefficient;

    SalaryCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
