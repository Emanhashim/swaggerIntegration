package com.bazra.usermanagement.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@EqualsAndHashCode

@Table(name = "the")
public class UserInfo implements UserDetails {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
	 private Long id;
	 private String firstname;
	 private String lastname;
	 private String password;
	 private String password2;
	 private String username;
	 private String email;
	 private String birthday;
	 @Enumerated(EnumType.STRING)
	 private Roles roles = Roles.USER ;
	 @Enumerated(EnumType.STRING)
	 private Levels levels = Levels.LEVEL_1;
	 private Boolean locked=true;
	 private Boolean enabled=true;
	 private String resetPasswordToken;
	 private String country;
	 private String gender;
	 private String region;
	 private String city;
	 private String Subcity;
	 private String Woreda;
	 private String HouseNo;
	 
	 
	 public UserInfo( String password, String username, String resetpasswordtoken) {
			
			this.password = password;
			this.username=username;
			this.resetPasswordToken = resetpasswordtoken;
			
		}
	


		
		public UserInfo(String firstname, String lastname, String password, String username) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.password = password;
			this.username=username;
			
		}
		
		public UserInfo(String firstname, String lastname, String password, String username,String gender) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.password = password;
			this.username=username;
			this.gender=gender;
			
		}
		
		public UserInfo(Roles role,Long id, String password, String username) {
			this.id = id;
			this.roles = role;
			this.password = password;
			this.username=username;
		}
		public UserInfo() {
			
		}
		
		 public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getEmail() {
				return email;
			}
		public void setEmail(String email) {
			this.email = email;
		}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubcity() {
		return Subcity;
	}

	public void setSubcity(String subcity) {
		Subcity = subcity;
	}

	public String getWoreda() {
		return Woreda;
	}

	public void setWoreda(String woreda) {
		Woreda = woreda;
	}

	public String getHouseNo() {
		return HouseNo;
	}

	public void setHouseNo(String houseNo) {
		HouseNo = houseNo;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public Levels getLevels() {
		return levels;
	}
	public void setLevels(Levels levels) {
		this.levels = levels;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//	public UserInfo(User user) {
//        this.firstname = user.getFirstname();
//        this.password = user.getPassword();
//        this.email=user.getEmail();
//        this.lastname=user.getLastname();
//        this.phone = user.getPhone();
////        this.username=user.getEmail();
//    }
	



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority= new SimpleGrantedAuthority(roles.name());
		return Collections.singletonList(authority);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities2() {
		SimpleGrantedAuthority authority= new SimpleGrantedAuthority(levels.name());
		return Collections.singletonList(authority);
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	
	public String getPassword2() {
		// TODO Auto-generated method stub
		return password2;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}


}
