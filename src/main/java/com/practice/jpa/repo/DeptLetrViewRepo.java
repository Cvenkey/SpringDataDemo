package com.practice.jpa.repo;

import com.practice.jpa.model.DeptLetr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DeptLetrViewRepo extends ReadOnlyRepository<DeptLetr,Long>,
        PagingAndSortingRepository<DeptLetr, Long> {
    @Query(value = "select * from pin_services.dept_letr dl where dl.deptno=:deptno", nativeQuery = true)
    Page<DeptLetr> findByViewDataDeptNo(int deptno, Pageable pageable);

    @Query(value = "select * from pin_services.dept_letr dl where dl.dept_name :deptNames", nativeQuery = true)
    List<DeptLetr> findUserByNameList(@Param("deptNames") Collection<String> deptNames);



}
