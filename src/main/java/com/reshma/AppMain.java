package com.reshma;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.reshma.configuration.AppConfig;
import com.reshma.model.Employee;
import com.reshma.service.EmployeeService;

public class AppMain
{

	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		EmployeeService service = (EmployeeService) context.getBean("employeeService");

		/*
		 * Create Employee1
		 */
		Employee employee1 = new Employee();
		employee1.setName("Reshma");
		employee1.setJoiningDate(new LocalDate(2015, 10, 10));
		employee1.setSalary(new BigDecimal(30000));
		employee1.setSsn("SSN00000005");

		/*
		 * Create Employee2
		 */
		Employee employee2 = new Employee();
		employee2.setName("Rupanagudi");
		employee2.setJoiningDate(new LocalDate(2012, 11, 11));
		employee2.setSalary(new BigDecimal(50000));
		employee2.setSsn("SSN00000006");

		/*
		 * Persist both Employees
		 */
		service.saveEmployee(employee1);
		service.saveEmployee(employee2);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employees = service.findAllEmployees();
		for (Employee emp : employees)
		{
			System.out.println(emp);
		}

		/*
		 * delete an employee
		 */
		service.deleteEmployeeBySsn("ssn00000002");

		/*
		 * update an employee
		 */

		Employee employee = service.findBySsn("ssn00000001");
		employee.setSalary(new BigDecimal(50000));
		service.updateEmployee(employee);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employeeList = service.findAllEmployees();
		for (Employee emp : employeeList)
		{
			System.out.println(emp);
		}

		context.close();
	}
}
