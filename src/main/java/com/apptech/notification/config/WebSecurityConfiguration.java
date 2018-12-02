package com.apptech.notification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//    	http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/notice").permitAll().antMatchers("/accountRegister").permitAll();
//    }
//    
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//			//auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/fonts/**", "/js/**", "/img/**","/surveys/**","/account/**");
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(11);
//	}
//}
