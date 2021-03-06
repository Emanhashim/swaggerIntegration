package com.bazra.usermanagement.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserInfo, Long> {
	 Optional<UserInfo> findByUsername(String username);
	 
	 Optional<UserInfo> findByResetPasswordToken(String resetPasswordToken);

}
