package com.bazra.usermanagement.signup;

import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.model.UserInfoService;
import com.bazra.usermanagement.model.UserRepository;
import com.bazra.usermanagement.signin.SignInResponse;

import lombok.AllArgsConstructor;
@CrossOrigin
@RestController
@RequestMapping("/api/users")
@ApiResponses(value ={
		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@AllArgsConstructor
public class SignUpController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	UserRepository userRepository ;
//	@Autowired
//	GenderRepository genderRepository;
	
	
	
	@PostMapping("/signup")
	@ApiOperation(value ="This is UserManagement Module For Signup new User")
	public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
		String pass1= request.getPassword();
        String pass2=request.getPassword2();
       
        
//        genderRepository.save(Gender.FEMALE);
//        genderRepository.save(Gender.MALE);
        
//        if(!pass1.matches(pass2)) {
//        	ResponseEntity
//            .badRequest()
//            .body(new SignUpResponse("Error: Passwords don't match!"));
//        }
		if(!pass1.matches(pass2)) {
			return  ResponseEntity
					.badRequest()
					.body(new SignUpResponse("Error: Passwords don't match!"));
		}

        UserInfo user = new UserInfo(
				request.getFirstname(), 
				request.getLastname(),
				passwordEncoder.encode(request.getPassword()),
	            request.getUsername());
        user.setCountry(request.getCountry());
        user.setBirthday(request.getBirthday());
        user.setEmail(request.getEmail());
        String strgender= request.getGender();
        
//        GenderInst gender2 = new GenderInst();
        if (strgender.matches("MALE") ) {
            
            user.setGender(strgender);
            
          } else if (strgender.matches("FEMALE")) {
        	 
        	
        	 user.setGender(strgender);
		}      
       
		return userInfoService.signUpUser(user);
	}
}
