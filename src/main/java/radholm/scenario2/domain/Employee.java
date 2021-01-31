package radholm.scenario2.domain;

import radholm.scenario2.common.SalaryCoefficient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
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
    private Boolean isManager;
    private Boolean isCEO;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
    @OneToMany(mappedBy = "manager")
    private List<Employee> subordinates = new ArrayList<Employee>();

    protected Employee() {

    }

    public Employee(String firstName, String lastName, Integer rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
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

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public Boolean getCEO() {
        return isCEO;
    }

    public void setCEO(Boolean CEO) {
        isCEO = CEO;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rank=" + rank +
                ", isManager=" + isManager +
                ", isCEO=" + isCEO +
                '}';
    }
}
