package com.practice.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "dept_letr")
@Getter
@ToString
@Immutable
public class DeptLetr {
    @EmbeddedId
    private DeptLetrId id;
    @Column(name="dept_name")
    private String deptName;
    @Column(name="let_name")
    private String letName;
    @Column(name="salary")
    private int salary;
}
