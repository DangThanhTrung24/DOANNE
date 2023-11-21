package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Address;
import com.trungdt.entity.District;
import com.trungdt.entity.Province;
import com.trungdt.entity.Ward;
import com.trungdt.model.AddressModel;

public interface AddressService {	
	// danh sách địa chỉ của người dùng hiện tại
	List<Address> findListAddressByEmail(String username);
	List<Province> findAllProvince();
	List<District> findDistrictByIdProvince(Integer id);
	List<Ward> findWardByIdProvinceAndIdDistrict(Integer idProvince, Integer idDistrict);
	// thêm địa chỉ mới
	AddressModel createAddress(AddressModel addressModel);
	// thông tin địa chỉ 
	Address getAddressById(int parseInt);
	// xóa địa chỉ
	void delete(Address address);
	// thông tin địa chỉ 
	Address findAddressById(String username, int id);
	// lấy địa chỉ bởi id
	AddressModel getOneAddressById(int id);
	List<District> getListDistrictByAdressId(Integer id);
	List<Ward> getListWardByAdressId(Integer id);
	AddressModel updateAddress(AddressModel addressModel, Integer id);
}
