package com.alkemy.ar.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alkemy.ar.security.filter.JwtRequestFilter;
import com.alkemy.ar.security.service.UserService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService  userService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override	//sobreescribo el service
	protected void configure (AuthenticationManagerBuilder auth)throws Exception{
		
		auth.userDetailsService(userService);
	}
	
	
	@Override
	@Bean  //esto es obligatorio sobreescribirlo
	public AuthenticationManager authenticationManagerBean()throws Exception{
		
		return super.authenticationManagerBean();
	}
	
	
	@Override //detalls que seguridad le vamos a dar al protocolo http, que requests aceptar y cuales no
	protected void configure (HttpSecurity  httpSecurity) throws Exception{
		
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/login","/register").permitAll()
		.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//stateless indica que no abra una sesion y que pida autenticacion por cada endpoint al que
		// uno acceda 
		
		//se agrega un filtro para cuando me manden una solicitud revisar que tengan un token valido
		httpSecurity.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean  //es el codificador del password
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();//esto indica que no se codifica la password
	}
	
}
