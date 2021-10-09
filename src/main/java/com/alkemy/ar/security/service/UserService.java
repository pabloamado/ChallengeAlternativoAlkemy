package com.alkemy.ar.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.security.dto.CredentialsDto;
import com.alkemy.ar.security.mapper.UserMapper;
import com.alkemy.ar.security.model.User;
import com.alkemy.ar.security.repository.UserRepository;
import com.alkemy.ar.service.EmailService;

import java.io.IOException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Override //carga un usuario segun el username pasado por parametro
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		//ir a la db para cargar el usuario
		com.alkemy.ar.security.model.User user=userRepository.findByUsername(username);
		
		if(user!=null) {
			
			return new org.springframework.security.core.userdetails.User
					(user.getUsername(),user.getPassword(),Collections.emptyList());//coleccion es la lista de roles
		}
		
		throw new UsernameNotFoundException(ErrorMsg.USERNAME_OR_PASSWORD_NOT_FOUND.toString());
		
	}

	public void registerUser(CredentialsDto credentialsDto) throws IOException {
		
			User user=userMapper.toUser(credentialsDto);
			
			user=userRepository.save(user);
			
			emailService.sendWelcomeEmail(user.getUsername());
			
		
	}

}
