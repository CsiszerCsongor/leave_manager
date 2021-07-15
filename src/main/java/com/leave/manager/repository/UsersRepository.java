package com.leave.manager.repository;

import com.leave.manager.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users loadUserByUsername(String username);

}
