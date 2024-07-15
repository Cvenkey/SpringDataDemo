package com.practice.jpa.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "Dept")
@Getter
@Setter
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="deptno")
    private long deptno;
    @Column(name="name")
    private String name;
    @Column(name="hod")
    private String hod;

//    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name="deptno")
//    private List<Lecturer> lecturers;


}
