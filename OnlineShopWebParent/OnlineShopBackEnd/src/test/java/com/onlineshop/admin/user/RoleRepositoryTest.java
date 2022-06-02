package com.onlineshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.onlineshop.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateFirstRole() {
		Role adminRole = new Role("Admin","manage everything");
		Role savedRole = roleRepository.save(adminRole);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("Salesperson","manage product price, customers, shipping, orders, sales report");
		Role roleEditor = new Role("Editor","manage categories, brands, product, articles and menus");
		Role roleShipper = new Role("Shipper","view products, view orders, and update order status");
		Role roleAssistant = new Role("Assistant","manage quesstions and reviews");
		
		roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}

}
