package com.huy.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.huy.config.GroupUserDetailsService;

@Configuration
@EnableWebSecurity
public class UserConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private GroupUserDetailsService groupUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(groupUserDetailsService);
    }
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.mailtrap.io");
	    mailSender.setPort(2525);

	    mailSender.setUsername("1f3566bd4fcc9f");
	    mailSender.setPassword("9314e59c6425c0");
		return mailSender;
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	  @Override protected void configure (HttpSecurity http) throws Exception {
		  http.csrf().disable();
	  http.authorizeRequests().antMatchers("user/join").permitAll()
	  .and().authorizeRequests().antMatchers("/").authenticated().and().formLogin();
	  }

	 
}
