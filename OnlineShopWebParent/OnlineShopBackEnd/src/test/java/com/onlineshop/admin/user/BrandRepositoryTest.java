package com.onlineshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.onlineshop.admin.brand.BrandRepository;
import com.onlineshop.common.entity.Brand;
import com.onlineshop.common.entity.Category;
import com.onlineshop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Test
	public void testCreateBrand1() {
		Category categoryLaptop = new Category(6);
		Brand acer = new Brand ("Acer", "brand-logo.png");
		acer.getCategories().add(categoryLaptop);
		
		Brand savedBrand = brandRepository.save(acer);
		
		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateBrand2() {
		Category categoryPhonesAndAccessories = new Category(4);
		Category categoryTablets = new Category(7);
		Brand apple = new Brand ("Apple", "brand-logo.png");
		apple.getCategories().add(categoryPhonesAndAccessories);
		apple.getCategories().add(categoryTablets);
		
		Brand savedBrand = brandRepository.save(apple);
		
		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateBrand3() {
		Category memory = new Category(29);
		Category internalHDD = new Category(24);
		Brand samsung = new Brand ("Samsung", "brand-logo.png");
		samsung.getCategories().add(memory);
		samsung.getCategories().add(internalHDD);
		
		Brand savedBrand = brandRepository.save(samsung);
		
		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAllBrands() {
		Iterable<Brand> listBrands = brandRepository.findAll();
		listBrands.forEach(brand -> System.out.println(brand));
		
		assertThat(listBrands).isNotEmpty();
	}
	
	@Test
	public void testGetById() {
		Integer brandId = 1;
		Brand brand = brandRepository.findById(brandId).get();
		System.out.println(brand);
		
		assertThat(brand).isNotNull();
	}
	
	@Test
	public void testUpdateName() {
		String newName = "Samsung Electronics";
		Brand brand = brandRepository.findById(4).get();
		brand.setName(newName);
		
		Brand newNameBrand = brandRepository.save(brand);
		
		assertThat(newNameBrand.getName()).isEqualTo(newName);
		
	}
	
	@Test
	public void testDelete() {
		Integer id = 5;
		brandRepository.deleteById(id);
		Optional<Brand> deletedBrand = brandRepository.findById(id);
		
		assertThat(deletedBrand.isEmpty());
	}

}
