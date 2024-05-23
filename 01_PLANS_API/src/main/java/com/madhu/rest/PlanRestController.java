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

import com.madhu.constants.AppConstants;
import com.madhu.entity.Plan;
import com.madhu.props.AppProperties;
import com.madhu.service.PlanService;

@RestController  
public class PlanRestController {

	private PlanService service;

	private Map<String, String> messages;

	public PlanRestController(PlanService service, AppProperties appProperties) {
		this.service = service;
		this.messages = appProperties.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = service.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String msg = AppConstants.EMPTY_STR;

		boolean isSaved = service.savePlan(plan);

		if (isSaved) {
			msg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}

		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getAllPlans() {

		List<Plan> plans = service.getAllPlans();
		return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan planById = service.getPlanById(planId);

		return new ResponseEntity<Plan>(planById, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {

		String msg = AppConstants.EMPTY_STR;

		boolean isUpdated = service.updatePlan(plan);

		if (isUpdated) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {

		String msg = AppConstants.EMPTY_STR;

		boolean isDeleted = service.deletePlanById(planId);

		if (isDeleted) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status) {

		String msg = AppConstants.EMPTY_STR;
		boolean isStatusChanged = service.planStatusChange(planId, status);
		if (isStatusChanged) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
