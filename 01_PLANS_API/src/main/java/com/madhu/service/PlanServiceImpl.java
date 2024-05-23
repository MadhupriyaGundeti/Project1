package com.madhu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.entity.Plan;
import com.madhu.entity.PlanCategory;
import com.madhu.exception.PlanNotFoundException;
import com.madhu.repo.PlanCategoryRepo;
import com.madhu.repo.PlanRepo;

@Service

public class PlanServiceImpl implements PlanService{
	
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> categories = planCategoryRepo.findAll();
		
		Map<Integer,String> categoryMap = new HashMap<>();
		
		categories.forEach(category->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		return categoryMap;  
	}

	@Override
	public boolean savePlan(Plan plan) {
		
		Plan saved = planRepo.save(plan);
		
		return saved.getPlanId()!=null;
		
	}

	@Override
	public List<Plan> getAllPlans() {
		
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		Plan plan = planRepo.findById(planId).orElseThrow(()->
		new PlanNotFoundException("Plan not found with id: "+planId));
		return plan;
		
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
		
		planRepo.save(plan);
		
		return plan.getPlanId()!=null;
		
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		Plan plan = getPlanById(planId);
		
		planRepo.delete(plan); 
		
		return true;
	}

	@Override
	public boolean planStatusChange(Integer planId, String activeSw) {
		
		Optional<Plan> opt = planRepo.findById(planId);
		
		if(opt.isPresent()) {
			Plan plan = opt.get();
			plan.setActiveSw(activeSw);
			planRepo.save(plan);
			return true;
			
		}
		
		return false;
	}
	
	
	
}
