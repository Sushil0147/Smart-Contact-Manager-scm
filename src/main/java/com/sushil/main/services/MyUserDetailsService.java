package com.sushil.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sushil.main.entities.User;
import com.sushil.main.entities.UserPrincipal;
import com.sushil.main.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
			
		}
		return new UserPrincipal(user);
	}

}
