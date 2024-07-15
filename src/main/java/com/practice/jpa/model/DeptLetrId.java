package com.practice.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Data
@Embeddable
public class DeptLetrId {
    @Column(name = "deptno")
    private long deptno;
    @Column(name = "lecturer_id")
    private long lecturerId;
}
