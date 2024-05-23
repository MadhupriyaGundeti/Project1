package com.madhu.rest;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.madhu.entity.Plan;
import com.madhu.service.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService service;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		
		Map<Integer, String> categories = service.getPlanCategories();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		
		String msg = "";
		
		boolean isSaved = service.savePlan(plan);
		
		if(isSaved) {
			msg = "Plan saved";
		}else {
			msg = "Plan not saved";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getAllPlans(){
		
		List<Plan> plans = service.getAllPlans();
		return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan planById = service.getPlanById(planId);
		
		return new ResponseEntity<Plan>(planById,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		
		String msg = "";
		
		boolean isUpdated = service.updatePlan(plan);
		
		if(isUpdated) {
			msg = "Plan updated";
		}else {
			msg="Plan not updated"; 
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	
	
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		
		String msg = "";
		
		boolean isDeleted = service.deletePlanById(planId);
		
		if(isDeleted) {
			msg = "Plan deleted";
		}else {
			msg = "Plan not deleted";
		}
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status){
		
		String msg = "";
		boolean isStatusChanged = service.planStatusChange(planId, status);
		if(isStatusChanged) {
			msg = "Status changed";
		}else {
			msg = "Status not changed";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
