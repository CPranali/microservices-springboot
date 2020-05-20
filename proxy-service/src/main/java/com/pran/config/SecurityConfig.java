package com.pran.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtConfig jwtConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()//Disabling CSRF
		//used stateless session as session won't be used to store user's state
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		//handle an unauthorized attempt
		.exceptionHandling().authenticationEntryPoint((req, rsp , e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		.and()
		//add filter to validate token with every request
		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		//authorization requests config
		.authorizeRequests()
		//allow all who are accessing auth service 
		.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
		//authorize swagger
		.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
		.antMatchers(HttpMethod.GET, "/petdetails/v2/api-docs/**").permitAll()
		.antMatchers(HttpMethod.GET, "/petshop/v2/api-docs/**").permitAll()
		//must be an Admin if trying to access admin area( authentication also required here)
		.antMatchers("/petshop/addpet/**").hasRole("ADMIN")
		.antMatchers("/petshop/updatepet/**").hasRole("ADMIN")
		.antMatchers("/petshop/deletepet/**").hasRole("ADMIN")
		// Any other request must be authenticated
        .anyRequest().authenticated();

	}	
	
}
