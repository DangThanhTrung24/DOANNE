package com.trungdt.service;

import com.trungdt.model.EmployeeForm;

public interface GeneralService {

	// thêm nhân viên (admin)
	EmployeeForm createEmployee(EmployeeForm employee);

	// hiển thị thông tin nhân viên (admin)
	EmployeeForm getOneUserById(Integer id);

	// cập nhật thông tin nhân viên (admin)
	EmployeeForm updateEmployee(EmployeeForm employeeForm);

}
