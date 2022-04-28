package com.bazra.usermanagement.model;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bazra.usermanagement.signin.SignInResponse;
import com.bazra.usermanagement.signup.SignUpResponse;
import com.bazra.usermanagement.signup.UpdateRequest;


@Service

public class UserInfoService implements UserDetailsService{
	
	private final static String msg = "user %s not found";
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo userInfo=userRepository.findByUsername(email).get();
		String passwor=userInfo.getPassword();
//		return userRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(msg, email)));
		return new User(email,passwor,new ArrayList<>());
		  
	}
	public ResponseEntity<?> signUpUser(UserInfo userInfo) {
        boolean userExists = userRepository
                .findByUsername(userInfo.getUsername())
                .isPresent();
       
        if (userExists) {
            
        	return ResponseEntity
            .badRequest()
            .body(new SignUpResponse("Error: Username is already taken!"));
        }
        
        userRepository.save(userInfo);
        return ResponseEntity.ok(new SignUpResponse("User registered successfully!"));
	}
	public String updatePassword(UpdateRequest request,String newpassword) {
		UserInfo userInfo=userRepository.findByUsername(request.getUsername()).get();
        userInfo.setFirstname(request.getFirstname());
        userInfo.setLastname(request.getLastname());
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(newpassword);
        userRepository.save(userInfo);

        return "successfully updated password";
        
    }
	
	public String updateResetpasswordtoken() {
		return "";
		
	}
	public ResponseEntity<?> signInUser(UserDetails username, String jwt) {
		UserInfo userInfo=userRepository.findByUsername(username.getUsername()).get();
		boolean userExists = userRepository
                .findByUsername(userInfo.getUsername())
                .isPresent();

	
		if(!userExists) {
			System.out.println("Wrong username or password");			
		}
			
		return ResponseEntity.ok(new SignInResponse(userInfo.getId(),userInfo.getUsername(),userInfo.getRoles(),userInfo.getCountry(),userInfo.getGender(),jwt));
	}
	

}
