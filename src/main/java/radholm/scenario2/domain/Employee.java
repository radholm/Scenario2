package radholm.scenario2.domain;

import radholm.scenario2.common.Role;

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
    private Boolean isCeo;
    @Transient
    private Role role;
    @ManyToOne
    @JoinColumn(name = "managerId", referencedColumnName = "Id")
    private Employee manager;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private final List<Employee> subordinates = new ArrayList<Employee>();

    protected Employee() {

    }

    public Employee(String firstName, String lastName, Integer rank, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.salary = rank * role.getSalaryCoefficient();
        this.isManager = false;
        this.isCeo = false;
        this.role = role;
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

    public Boolean getIsCeo() {
        return isCeo;
    }

    public void setIsCeo(Boolean CEO) {
        isCeo = CEO;
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
                ", isCEO=" + isCeo +
                ", manager=" + manager +
                ", subordinates=" + subordinates +
                '}';
    }
}
