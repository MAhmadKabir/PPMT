package com.example.ppm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ppm.domain.User;
import com.example.ppm.exceptions.UsernameAlreadyExistsException;
import com.example.ppm.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){
     
    	try {
    		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        //Username has to be unique (exception)
    		newUser.setUsername(newUser.getUsername());
    		

          // Make sure that password and confirmPassword match
          // We don't persist or show the confirmPassword
        return userRepository.save(newUser);}
    	catch(Exception e) {
    		throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
    	}
    }
}