package radholm.scenario2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import radholm.scenario2.domain.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.is_manager = 1", nativeQuery = true)
    List<Employee> findAllManagers();

    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.manager_id = :superiorId", nativeQuery = true)
    List<Employee> findAllSubordinates(@Param("superiorId") Long superiorId);

    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.isceo = 1", nativeQuery = true)
    Optional<Employee> findCEO();
}
