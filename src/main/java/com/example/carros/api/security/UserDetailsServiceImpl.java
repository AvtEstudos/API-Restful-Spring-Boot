package com.example.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.carros.domain.UserRepository;

@Service(value = "userDatailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;	

	//Unico método definido na interface a ser implementado
	@Override                                                  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
					
		com.example.carros.domain.User user = userRep.findByLogin(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return User.withUsername(username).password(user.getSenha()).roles("USER").build();		
	}	
}
