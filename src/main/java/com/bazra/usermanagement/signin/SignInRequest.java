package com.bazra.usermanagement.signin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@ApiModel
public class SignInRequest {

    @ApiModelProperty(value= "This is users Phone Number ")
    private String username;

    @ApiModelProperty(value= "This is users Phone Number ")
    private String password;

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public SignInRequest(String username, String password) {
//        this.setUsername(username);
//        this.setPassword(password);
//    }

}
