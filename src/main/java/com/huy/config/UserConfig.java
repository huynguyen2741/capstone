package com.huy.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.huy.service.UserService;

//import com.huy.config.GroupUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class UserConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
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
			  
			  http			
			  .authorizeRequests().antMatchers("/admin/join").permitAll()
			  .and().authorizeRequests().antMatchers("/user/join").permitAll()
			  .and().authorizeRequests().antMatchers("/product/products").hasAnyAuthority("ADMIN","USER")
			  .and().authorizeRequests().antMatchers("/cart/total").hasAnyAuthority("ADMIN","USER")
			  .and().authorizeRequests().antMatchers("/user/*").hasAuthority("USER")
			  .and().authorizeRequests().antMatchers("/cart/*").hasAuthority("USER")			  
			  .and().authorizeRequests().antMatchers("/admin/*").hasAuthority("ADMIN")
			  .and().authorizeRequests().antMatchers("/product/*").hasAuthority("ADMIN")		  
			  .and().authorizeRequests().anyRequest().authenticated()
			  .and().formLogin().and().httpBasic();
			  
	  }

	  @Bean
	  public DaoAuthenticationProvider daoAuthenticationProvider() {
	      DaoAuthenticationProvider provider =
	              new DaoAuthenticationProvider();
	      provider.setPasswordEncoder(passwordEncoder());
	      provider.setUserDetailsService(userService);
	      return provider;
	  }
}
