package com.alkemy.ar.security.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ar.security.dto.CredentialsDto;
import com.alkemy.ar.security.dto.JwtResponse;
import com.alkemy.ar.security.service.JwtUtilsService;
import com.alkemy.ar.security.service.UserService;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	@Autowired
	JwtUtilsService jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody CredentialsDto credentialsDto){
		
		UserDetails userDetails;
		
		try {
			
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentialsDto.getUsername()
							, credentialsDto.getPassword()));
			
			userDetails= (UserDetails) authentication.getPrincipal();
			
		}catch(BadCredentialsException e) {
			
			throw new BadCredentialsException("Usuario o contraseña incorrecta",e);
		}
		
		
		
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody CredentialsDto credentialsDto) throws IOException{
		
		userService.registerUser(credentialsDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
