package com.practice.jpa.service;

import com.practice.jpa.model.DeptLetr;
import com.practice.jpa.model.Employee;
import com.practice.jpa.repo.DeptLetrViewRepo;
import com.practice.jpa.repo.EmployeeRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JPADataService {

    @PostConstruct
    public void init(){
        System.out.println("this is initialization");
    }

    @Autowired
    DeptLetrViewRepo deptLetrViewRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @PersistenceContext
    private EntityManager entityManager;




    public Map<String, Object> getViewData(){
        int page = 0;
        int size = 4;

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("salary").descending().and(Sort.by("letName")));
        Page<DeptLetr> deptLecturerPage = deptLetrViewRepo.findAll(pageable);

        int totalPages = deptLecturerPage.getTotalPages(); // Fetch total pages once
        List<DeptLetr> allDeptLecturers = new ArrayList<>(deptLecturerPage.getContent());

        while (deptLecturerPage.hasNext()) {
            pageable = deptLecturerPage.nextPageable();
            deptLecturerPage = deptLetrViewRepo.findAll(pageable);
            allDeptLecturers.addAll(deptLecturerPage.getContent());
        }
        return fillResponse(allDeptLecturers, deptLecturerPage, totalPages);
    }

    public Map<String, Object> getViewDataBySlice(){
        int page = 0;
        int size = 4;

        Pageable pageable = PageRequest.of(page, size);
        Slice<DeptLetr> deptLecturerSlice;
        List<DeptLetr> allDeptLecturers = new ArrayList<>();

        do {
            deptLecturerSlice = deptLetrViewRepo.findAll(pageable);
            allDeptLecturers.addAll(deptLecturerSlice.getContent());
            pageable = deptLecturerSlice.nextPageable();
        } while (deptLecturerSlice.hasNext());

        return fillResponse(allDeptLecturers, null, deptLecturerSlice.getSize());
    }

    public Map<String, Object> getPaginatedDeptLecturers(Map<String,Object> searchParams) {
        Pageable pageable = PageRequest.of((int) searchParams.get("page"), (int) searchParams.get("size"));
        Page<DeptLetr> deptLecturerPage = deptLetrViewRepo.findAll(pageable);
        List<DeptLetr> allDeptLecturers = new ArrayList<>(deptLecturerPage.getContent());
        int totalPagesCount = searchParams.get("totalPages") == null ?
                deptLecturerPage.getTotalPages() : (int) searchParams.get("totalPages");
        return fillResponse(allDeptLecturers, deptLecturerPage, totalPagesCount);
    }

    private Map<String, Object> fillResponse(List<DeptLetr> allDeptLecturers, Page<DeptLetr> deptLecturerPage, int totalPagesCount) {
        Map<String, Object> response = new HashMap<>();
        response.put("list", allDeptLecturers);
        if(null!=deptLecturerPage) {
            response.put("currentPage", deptLecturerPage.getNumber());
            response.put("recordsPerPage", deptLecturerPage.getSize());
        }
        response.put("totalPages", totalPagesCount);
        return response;
    }

    public List<DeptLetr> findByDeptNo(int deptno){
        Pageable pageable = PageRequest.of(0, 4, Sort.by("let_name"));
        Page<DeptLetr> deptLetrs = deptLetrViewRepo.findByViewDataDeptNo(2,pageable);
        return deptLetrs.getContent();
    }


    public int updateEmployee(double sal, int deptno){
        return employeeRepo.updateEmployeesSalaries(sal,deptno);
    }


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public List<Employee> getEmployeesByDeptNo(int dept){
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetEmployees", Employee.class);
//        query.registerStoredProcedureParameter("deptno", Double.class , ParameterMode.IN);
//        query.setParameter("deptno", dept);
//        return query.getResultList();
//    }

    public List<Employee> getEmployeesByDeptNo(int dept){
        return employeeRepo.getEmployees(dept);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bean about to destroy");
    }

}
