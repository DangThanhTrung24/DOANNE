package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Employee;
import com.trungdt.model.EmployeeModel;

/**
 * Class cung cap cac dich vu thao tac voi table Employee trong database
 */
public interface EmployeeService {

	List<EmployeeModel> getListEmployee();

	void save(Employee employee);
	
}
