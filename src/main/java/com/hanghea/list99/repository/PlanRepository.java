package com.hanghea.list99.repository;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    //    List<Plan> findByUser(User user);
    Page<Plan> findAllByUserId(Long userId, Pageable pageable);
}

