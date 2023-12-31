package com.codewithakshay.cyperts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.entity.Employees;
import com.codewithakshay.cyperts.repository.EmployeeRepository;
import com.codewithakshay.cyperts.service.EmployeeService;
import com.codewithakshay.cyperts.util.ExcelHelper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employees> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public void exportToTable(MultipartFile file) {
		try {
			List<Employees> empData = ExcelHelper.excelToEmployeeConverter(file.getInputStream());
			employeeRepository.saveAll(empData);
		} catch (Exception e) {
			throw new RuntimeException("Unable to Strore Excel Data : " + e.getMessage());
		}

	}

}
