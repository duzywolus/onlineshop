package com.onlineshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.onlineshop.common.entity.Role;
import com.onlineshop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTomek = new User("besttshoppp@gmail.com", "nowe123", "Tomasz", "Wolek");
		userTomek.addRole(roleAdmin);
		
		User savedUser = userRepository.save(userTomek);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateUserWithTwoRoles() {
		
		User userJanek = new User("janekzydek@gmail.com", "janek123", "Janek", "Zydek");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userJanek.addRole(roleEditor);
		userJanek.addRole(roleAssistant);
		
		User savedUser = userRepository.save(userJanek);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAllUsers() {
		Iterable<User> listUsers = userRepository.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		User userTomek = userRepository.findById(4).get();
		System.out.println(userTomek);
		assertThat(userTomek).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userTomek = userRepository.findById(4).get();
		userTomek.setEnabled(true);
		userTomek.setEmail("tomaszwolek@gmail.com");
		
		userRepository.save(userTomek);
		
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userJanek = userRepository.findById(5).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userJanek.getRoles().remove(roleEditor);
		userJanek.addRole(roleSalesperson);
		
		userRepository.save(userJanek);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 5;
		userRepository.deleteById(userId);
		
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "tomaszwolek@gmail.com";
		User user = userRepository.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById(){
		Integer id = 4;
		Long countById = userRepository.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Integer id = 4;
		userRepository.updateEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Integer id =4;
		userRepository.updateEnabledStatus(id, true);
	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	@Test
	public void testSearchUsers() {
		String keyword = "bruce";
		
		int pageNumber = 0;
		int pageSize = 4;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userRepository.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
	}
	
}
