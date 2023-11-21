package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Manufacturer;
import com.trungdt.model.ManufacturerModel;
import com.trungdt.service.ManufacturerService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/manufactures")
public class ManufacturerRestController {
	@Autowired
	ManufacturerService manufacturerService;
	
	// thêm nhà cung cấp mới cho shop (admin)
	@PostMapping("/form")
	public ManufacturerModel create(@RequestBody ManufacturerModel manufacturerModel) {
		return manufacturerService.createManufacturer(manufacturerModel);
	}
	
	// hiển thị tất cả thưởng hiệu trang (admin)
	@GetMapping()
	public List<Manufacturer> getAll(){
		return manufacturerService.findAll();
	}
	
	// hiển thị thông tin thương hiệu được chọn (admin)
	@GetMapping("/form/{id}")
	public ManufacturerModel getOneUserById(@PathVariable("id") Integer id) {
		return manufacturerService.getOneManufacturerById(id);
	}
	
	// xóa thuognw hiệu (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		manufacturerService.delete(id);
	}
	
	// cập nhật thương hiệu được chọn (admin)
	@PutMapping("/form/{id}")
	public ManufacturerModel update(@PathVariable("id") Integer id, @RequestBody ManufacturerModel manufacturerModel) {
		return manufacturerService.updateManufacturer(manufacturerModel);
	}
}
