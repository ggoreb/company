package com.example.company;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.example.company.model.Countries;
import com.example.company.model.Departments;
import com.example.company.model.Employees;
import com.example.company.model.Locations;
import com.example.company.model.NightPharmacy;
import com.example.company.model.Regions;
import com.example.company.repository.DepartmentsRepository;
import com.example.company.repository.EmployeesRepository;
import com.example.company.repository.LocationsRepository;
import com.example.company.repository.NightPharmacyRepository;
import com.example.company.repository.RegionsRepository;

@SpringBootTest
class CompanyApplicationTests {
	@Autowired
	EmployeesRepository employeesRepository;

	@Autowired
	DepartmentsRepository departmentsRepository;

	@Autowired
	RegionsRepository regionsRepository;

	@Autowired
	LocationsRepository locationsRepository;

	@Autowired
	NightPharmacyRepository nightPharmacyRepository;

	@Test
	void 페이징연습() {
		Pageable pageable = PageRequest.of(1, 10);

		Page<NightPharmacy> page = nightPharmacyRepository.findAll(pageable);
		List<NightPharmacy> list = page.getContent();
		System.out.println(list);
	}

	@Test @Transactional
	void 문제4번() {
		Locations location = 
				locationsRepository.findByStateProvince("Washington");
		List<Departments> depts = 
				location.getDepartments();
		for(Departments d : depts) {
			String 부서명 = d.getDepartmentName();
			List<Employees> emps = d.getEmployees();
			int 사원수 = emps.size();
			
			System.out.println(부서명 + " " + 사원수);
		}
	}

	@Test @Transactional
	void 문제3번() {
		Regions region = regionsRepository.findByRegionName("Asia");
		List<Countries> countries = region.getCountries();
		for(Countries c : countries) {
			String 국가명 = c.getCountryName();
			System.out.println(국가명);
		}
	}

	@Test @Transactional
	void 문제2번() {
		Departments department = 
		    departmentsRepository.findByDepartmentName("IT");
		List<Employees> emps = department.getEmployees();
		int 사원수 = emps.size();
		System.out.println(사원수);
		for(int i = 0; i < emps.size(); i++) {
			Employees emp = emps.get(i);
			int 급여 = emp.getSalary();
			String 이름 = emp.getFirstName();
			System.out.println(급여 + 이름);
	  }
	}

	@Test
	void 문제1번() {
		Optional<Employees> opt = 
				employeesRepository.findById(140);
		Employees employees = opt.get();

		int 사원번호 = employees.getEmployeeId();
		String 성 = employees.getLastName();
		String 이름 = employees.getFirstName();
		Countries c = 
				employees.getDepartments().getLocations().getCountries();
		String 나라명 = c.getCountryName();
		System.out.printf(
			"사원번호: %s, 성: %s, 이름: %s, 나라명: %s", 사원번호, 성, 이름, 나라명
		);
		System.out.println(사원번호);
		System.out.println(성);
		System.out.println(이름);
		System.out.println(나라명);
	}

}
