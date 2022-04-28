package com.bazra.usermanagement.signin;

import java.io.IOException;

import javax.management.loading.PrivateClassLoader;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.model.UserInfoService;
import com.bazra.usermanagement.model.UserRepository;
import com.bazra.usermanagement.signup.SignUpResponse;
import com.bazra.usermanagement.util.JwtUtil;





@RestController
@ApiResponses(value ={
		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@RequestMapping("/api/users")
public class SignInController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserInfoService userInfoService;
	
//	@Autowired
	private UserInfo userInfo;
	
	private UserDetails userDetails;
//	
//	private String jwt;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
		
	@PostMapping("/signin")
	@ApiOperation(value ="This is UserManagement Module For Sign in User with (Username and Password)")
	public ResponseEntity<?> createAuthenticationToken( @RequestBody SignInRequest authenticationRequest) throws AuthenticationException  {

		boolean userExists = userRepository
                .findByUsername(authenticationRequest.getUsername())
                .isPresent();
		if(userExists) {
			userDetails = userInfoService
					.loadUserByUsername(authenticationRequest.getUsername());
			userInfo=userRepository.findByUsername(authenticationRequest.getUsername()).get();
		}
		
		else{
          
			 return ResponseEntity
            .badRequest()
            .body(new SignUpResponse("Error: Username does not exist"));
	      
		} 
		userInfo =	userRepository.findByUsername(authenticationRequest.getUsername()).get();
  Authentication authentication =   authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
  SecurityContextHolder.getContext().setAuthentication(authentication);

  userDetails = userInfoService
			.loadUserByUsername(authenticationRequest.getUsername());
	final String jwt= jwtUtil.generateToken(userDetails);
		userRepository.findByUsername(authenticationRequest.getUsername()).get().setResetPasswordToken(jwt);
		userInfo =	userRepository.findByUsername(authenticationRequest.getUsername()).get();
			
//		} 

		

		return ResponseEntity.ok(new SignInResponse(userInfo.getId(),userInfo.getUsername(),userInfo.getRoles(),userInfo.getCountry(),userInfo.getGender(),jwt));

	}
	
	

}
