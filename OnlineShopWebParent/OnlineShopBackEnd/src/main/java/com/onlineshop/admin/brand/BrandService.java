package com.onlineshop.admin.brand;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.common.entity.Brand;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> listAll() {
		return (List<Brand>) brandRepository.findAll();
	}
	
	public Brand save (Brand brand) {
		return brandRepository.save(brand);
	}
	
	public Brand get(Integer id) throws BrandNotFoundException{
		try {
			return brandRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new BrandNotFoundException("Could not find any brand widh this ID: " + id);
		}
	}
	
	public void delete (Integer id) throws BrandNotFoundException {
		Long countById = brandRepository.countById(id);
		
		if (countById == null || countById == 0) {
			throw new BrandNotFoundException("Could not find any brand widh this ID: " + id);
		}
		
		brandRepository.deleteById(id);
	}
	
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		Brand brandByName = brandRepository.findByName(name);
		
		if (isCreatingNew = true) {
			if (brandByName != null) {
				return "Duplicate";
			}
		}else {
				if (brandByName !=null && brandByName.getId() != id) {
					return "Duplicate";
				}
			}
			
			return "OK";
		
	}
	
}
