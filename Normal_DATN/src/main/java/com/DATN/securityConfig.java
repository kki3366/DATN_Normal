package com.DATN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.DATN.Service.UserService;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserService userService;
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()

			.antMatchers("/order/**","/cart","/addCartO/**","/addCartI/**","/orderHistory","/orderHistoryDetail","/checkout").authenticated()
			.antMatchers("/ContactUs/form").authenticated()
			.antMatchers("/admin/**").hasAnyRole(true+"")
			.antMatchers("/rest/authorities").hasRole(true+"")
			.antMatchers("/api/**").hasRole(true+"")

			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/index",false)
			.failureUrl("/security/error");
		
		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried");
		
		http.logout()
			.logoutUrl("/security/logout")
			.logoutSuccessUrl("/security/logout/success");
		
		
		http.oauth2Login()
			.loginPage("/security/login/form")
			.defaultSuccessUrl("/loginSuccess",true)
			.failureUrl("/security/error")
			.authorizationEndpoint()
			    .baseUri("/oauth2/authorization");



	}

}
