package com.practice.jpa.repo;

import com.practice.jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Employee e SET e.salary = :salary WHERE e.deptno = :deptno")
    int updateEmployeesSalaries(double salary, int deptno);

//    @Procedure(procedureName = "GetEmployees")
//    List<Employee> getEmployees(@Param("deptno") int deptno);

    @Query(value = "CALL GetEmployees(:deptnum)", nativeQuery = true)
    List<Employee> getEmployees(@Param("deptnum") int deptnum);
}
