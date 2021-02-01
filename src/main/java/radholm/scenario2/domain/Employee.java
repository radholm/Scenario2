package radholm.scenario2.domain;

import radholm.scenario2.common.SalaryCoefficient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private Integer rank;
    private Double salary;
    private Boolean isManager;
    private Boolean isCEO;
    @ManyToOne
    @JoinColumn(name = "managerId", referencedColumnName = "Id")
    private Employee manager;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private final List<Employee> subordinates = new ArrayList<Employee>();

    protected Employee() {

    }

    public Employee(String firstName, String lastName, Integer rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.salary = rank * SalaryCoefficient.EMPLOYEE.getCoefficient();
        this.isManager = false;
        this.isCEO = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    //public Employee getManager() {
    //    return manager;
    //}

    public void setManager(Employee manager) {
        manager.setIsManager(true);
        this.manager = manager;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean getIsCEO() {
        return isCEO;
    }

    public void setIsCEO(Boolean CEO) {
        isCEO = CEO;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Employee employee) {
        this.subordinates.add(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rank=" + rank +
                ", salary=" + salary +
                ", isManager=" + isManager +
                ", isCEO=" + isCEO +
                ", manager=" + manager +
                ", subordinates=" + subordinates +
                '}';
    }
}
