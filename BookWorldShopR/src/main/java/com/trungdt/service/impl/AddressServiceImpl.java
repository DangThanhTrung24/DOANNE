package com.trungdt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trungdt.dao.AddressDao;
import com.trungdt.dao.ProvinceDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Address;
import com.trungdt.entity.District;
import com.trungdt.entity.Province;
import com.trungdt.entity.User;
import com.trungdt.entity.Ward;
import com.trungdt.model.AddressModel;
import com.trungdt.service.AddressService;

@Service
@Repository
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	UserDao userDao;
	
	// danh sách địa chỉ người dùng đang đăng nhập
	@Override
	public List<Address> findListAddressByEmail(String username) {
		return addressDao.findListAddressByEmail(username);
	}

	RestTemplate rest = new RestTemplate();
	String url = "https://addressapiproject-default-rtdb.firebaseio.com/province.json";
	
	@Override
	public List<Province> findAllProvince() {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		return list;
	}

	@Override
	public List<District> findDistrictByIdProvince(Integer id) {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);	
		List<District> listDistrict = list.get(id).getDistricts();		
		return listDistrict;
	}

	@Override
	public List<Ward> findWardByIdProvinceAndIdDistrict(Integer idProvince, Integer idDistrict) {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);	
		List<District> listDistrict = list.get(idProvince).getDistricts();
		
		List<Ward> listWard = listDistrict.get(idDistrict).getWards();
		
		return listWard;
	}

	// thêm địa chỉ
	@Override
	public AddressModel createAddress(AddressModel addressModel) {
		// lấy thông tin user đang đăng nhập
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		User temp = userDao.findUserByEmail(username);
//		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);	
//		Province province = list.get(Integer.parseInt(addressModel.getProvince()));
//		District district = province.getDistricts().get(Integer.parseInt(addressModel.getDistrict()));
//		Ward ward = district.getWards().get(Integer.parseInt(addressModel.getWard()));
		
		Address address = new Address();
		address.setFullname(addressModel.getFullName());
		address.setPhone(addressModel.getPhone());
		address.setDetail(addressModel.getDetail());
		address.setProvince(addressModel.getProvince());
		address.setDistrict(addressModel.getDistrict());
		address.setWard(addressModel.getWard());
		// set địa chỉ thành địa chỉ của người đang đăng nhập
		address.setUser(temp);
		// lưu địa chỉ vào database
		addressDao.save(address);	
		return addressModel;
	}

	// thông tin địa chỉ dựa vào id
	@Override
	public Address getAddressById(int id) {	
		return addressDao.findById(id).get();
	}

	// xóa địa chỉ
	@Override
	public void delete(Address address) {		
		addressDao.delete(address);
	}

	// lấy địa chỉ theo id
	@Override
	public Address findAddressById(String username, int id) {
		return addressDao.findAddressById(username, id);
	}

	// lấy địa chỉ dựa vào id
	@Override
	public AddressModel getOneAddressById(int id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Address address = addressDao.findAddressById(username, id);
		
		AddressModel addressModel = new AddressModel();
		
		addressModel.setFullName(address.getFullname());
		addressModel.setPhone(address.getPhone());
		addressModel.setDetail(address.getDetail());
		
		addressModel.setProvince(address.getProvince());
		addressModel.setDistrict(address.getDistrict());
		addressModel.setWard(address.getWard());
		
		return addressModel;
	}

	@Override
	public List<District> getListDistrictByAdressId(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Address address = addressDao.findAddressById(username, id);
		
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		
		List<District> listDistrict = new ArrayList<>();
		
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(address.getProvince())) {				
				listDistrict = list.get(i).getDistricts();				
				break;
			}	
		}
		return listDistrict;
	}

	@Override
	public List<Ward> getListWardByAdressId(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Address address = addressDao.findAddressById(username, id);		
		
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		
		List<District> listDistrict = new ArrayList<>();
		List<Ward> listWard = new ArrayList<>();
		
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(address.getProvince())) {				
				listDistrict = list.get(i).getDistricts();				
				for(int j = 0; j<listDistrict.size(); j++) {
					if(listDistrict.get(j).getName().equals(address.getDistrict())) {
						listWard = listDistrict.get(j).getWards();
						break;
					}
				}
				break;
			}	
		}
		return listWard;
	}

	@Override
	public AddressModel updateAddress(AddressModel addressModel, Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
//		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);	
//		Province province = list.get(Integer.parseInt(addressModel.getProvince()));
//		District district = province.getDistricts().get(Integer.parseInt(addressModel.getDistrict()));
//		Ward ward = district.getWards().get(Integer.parseInt(addressModel.getWard()));
		User temp = userDao.findUserByEmail(username);
		Address address = addressDao.findAddressById(username, id);
		
		address.setFullname(addressModel.getFullName());
		address.setPhone(addressModel.getPhone());
		address.setDetail(addressModel.getDetail());
		address.setProvince(addressModel.getProvince());
		address.setDistrict(addressModel.getDistrict());
		address.setWard(addressModel.getWard());
		address.setUser(temp);
		addressDao.save(address);
		
		return addressModel;
	}


}
