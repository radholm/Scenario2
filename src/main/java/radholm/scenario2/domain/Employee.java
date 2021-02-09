package radholm.scenario2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import radholm.scenario2.common.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for each employee in the organization
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rank;
    private Double salary;
    private Boolean isManager;
    private Boolean isCeo;
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
        this.isManager = role.getIsManager();
        this.isCeo = role.getIsCeo();
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

    @JsonIgnore
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
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
                ", isCeo=" + isCeo +
                ", manager=" + manager +
                ", subordinates=" + subordinates +
                '}';
    }
}
