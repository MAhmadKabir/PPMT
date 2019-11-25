package com.example.ppm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ppm.domain.User;
import com.example.ppm.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user==null) {
			new UsernameNotFoundException("User Not Found");
		}
//		 GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//	       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),null);
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		if(user==null) {
			new UsernameNotFoundException("User Not Found");
		}
		return user;
	}

}
