package com.example.carros.api.security;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDatailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	//Unico método definido na interface a ser implementado
	@Override                                                  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		if (username.equals("user")) {
			return User.withUsername(username).password(enconder.encode("user")).roles("USER").build();
		} else if (username.equals("admin")) {
			return User.withUsername(username).password(enconder.encode("admin")).roles("USER", "ADMIN").build();
		}	
		
		throw new UsernameNotFoundException("User not found");
	}	
}
