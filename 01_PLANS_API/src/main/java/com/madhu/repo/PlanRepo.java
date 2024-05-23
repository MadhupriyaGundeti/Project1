package com.madhu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
