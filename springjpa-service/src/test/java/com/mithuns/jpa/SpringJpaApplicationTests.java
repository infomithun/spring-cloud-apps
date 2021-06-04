package com.mithuns.jpa;

import com.mithuns.jpa.controller.EmployeeController;
import com.mithuns.jpa.model.Employee;
import com.mithuns.jpa.repository.EmployeeRepository;
import com.mithuns.jpa.service.EmployeeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SpringJpaApplicationTests {

	private static Employee employee;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeAll
	public static void init() {
		employee = new Employee();
		int id = 100;
		employee.setId(id);
		employee.setFirstName("Little");
		employee.setLastName("Johan");
		employee.setAge(5);
		employee.setDesignation("Little Coder");
	}

	@Test
	public void testSaveNewEmployee() {
		Employee emp = employeeController.addEmployee(employee);
		Mockito.verify(employeeService, Mockito.times(1)).save(employee);
	}

	@Test
	public void testFindEmployeeById() {
		Mockito.when(employeeService.get(100)).thenReturn(employee);
		Employee emp = employeeController.findEmployeeById(100);
		Assert.assertSame(emp, employee);
	}

	@Test
	public void testFindById() {
		Employee emp = employeeController.findEmployeeById(100);
		Mockito.verify(employeeService, Mockito.times(1)).get(100);
	}
}
