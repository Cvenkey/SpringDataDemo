package com.practice.jpa.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "empl")
@Data
@ToString
@NamedStoredProcedureQuery(name="GetEmployees", procedureName = "GetEmployees",  parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "deptno", type = Integer.class)
})
public class Employee implements Serializable {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "empno")
    private Long empno;
    @Column(name = "ename")
    private String ename;
    @Column(name = "job")
    private String job;
    @Column(name = "comm")
    private Double comm;
    @Column(name = "sal")
    private Double salary;
    @Column(name = "deptno")
    private Integer deptno;
    @Column(name = "hiredate")
    private LocalDate hireDate;
}
