package com.hanghea.list99.repository;

import com.hanghea.list99.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
