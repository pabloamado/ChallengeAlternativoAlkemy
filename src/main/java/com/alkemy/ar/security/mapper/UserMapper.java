package com.alkemy.ar.security.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ar.security.dto.CredentialsDto;
import com.alkemy.ar.security.model.User;

@Component
public class UserMapper {

	public User toUser(CredentialsDto credentials) {
		
		User user=new User();
		user.setUsername(credentials.getUsername());
		user.setPassword(credentials.getPassword());
	
		return user;
	}
}
