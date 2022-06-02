package com.onlineshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.onlineshop.common.entity","com.onlineshop.admin.user"})
public class OnlineShopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopBackEndApplication.class, args);
	}

}
