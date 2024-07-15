package com.practice.jpa;

import com.practice.jpa.model.DeptLetr;
import com.practice.jpa.service.JPADataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication implements CommandLineRunner {

	@Autowired
	JPADataService jpaDataService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		displayViewData();
		displayPaginatedViewData(); //Used Pagination
		displayViewDataBySlice(); // Used Slice
		fetchViewDataByDeptNo(); //user defined query with pagination and sorting
		getEmployeesByDeptNo(); // called stored procedure
	}

	private void displayPaginatedViewData() {
		Map<String, Object > requestData = new HashMap<>();
		requestData.put("page", 2);
		requestData.put("size", 4);
		Map<String,Object> responseData =jpaDataService.getPaginatedDeptLecturers(requestData);
		responseData.entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+" "+entry.getValue()));
	}

	void displayViewData(){
		Map<String,Object> responseData =jpaDataService.getViewData();
		responseData.entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+" "+entry.getValue()));
	}
	void displayViewDataBySlice(){
		System.out.println("Display View Data By Slice ");
		Map<String,Object> responseData =jpaDataService.getViewDataBySlice();
		responseData.entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+" "+entry.getValue()));
	}
	void fetchViewDataByDeptNo(){
		System.out.println("Display View Data fetchViewDataByDeptNo ");
		List<DeptLetr> responseData =jpaDataService.findByDeptNo(2);
		responseData.forEach(rs-> System.out.println(rs.toString()));
	}

	void updateEmployee(){
		double sal = 1200000d;
		int dept =1;
		System.out.println("no.of employees effected: "+jpaDataService.updateEmployee(sal,dept));
	}


	void getEmployeesByDeptNo(){
		int deptNo = 10;
		System.out.println("Calling Stored Procedure");
		jpaDataService.getEmployeesByDeptNo(deptNo).stream().forEach(e-> System.out.println(e.toString()));
	}
}
