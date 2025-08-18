package com.wipro.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dao.LeaveRepository;
import com.wipro.entity.Leave;

@RestController
@RequestMapping("/leaves")
public class LeaveController {
	
	private final LeaveRepository leaveRepo;
	
	public LeaveController(LeaveRepository leaveRepo) {
		this.leaveRepo = leaveRepo;
	}
	
	// POST - Add leave
	@PostMapping
	public ResponseEntity<String> addLeave(@RequestBody Leave leave) {
		if (leave.getLeaveDays() <= 0) {
			throw new IllegalArgumentException("Leave days must be greater than 0");
		}
		leaveRepo.save(leave);
		return ResponseEntity.ok("Leave Added Successfully");
	}
	
	// GET - all leaves
	@GetMapping
	public List<Leave> getAllLeaves() {
		return leaveRepo.findAll().stream()
				.sorted(Comparator.comparing(Leave::getLeaveStatus).reversed()
						.thenComparing(Leave::getStartDate))
				.collect(Collectors.toList());
	}
	
	// GET - by ID
	@GetMapping("/{id}")
	public ResponseEntity<Leave> getLeaveById(@PathVariable int id) {
		Leave leave = leaveRepo.findById(id).orElse(null);  // fixed
		if (leave == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(leave);
	}
	
	// PUT - update leave
	@PutMapping("/{id}")
	public ResponseEntity<String> updateLeave(@PathVariable int id, @RequestBody Leave updateLeave) {
		Leave leave = leaveRepo.findById(id).orElse(null);  // fixed
		if (leave == null) {
			return ResponseEntity.notFound().build();
		}
		leave.setEmployeeName(updateLeave.getEmployeeName());
		leave.setLeaveType(updateLeave.getLeaveType());
		leave.setStartDate(updateLeave.getStartDate());
		leave.setEndDate(updateLeave.getEndDate());
		leave.setLeaveDays(updateLeave.getLeaveDays());
		leave.setLeaveStatus(updateLeave.getLeaveStatus());
		
		leaveRepo.save(leave);  // missing in your code
		return ResponseEntity.ok("Leave updated successfully");
	}
	
	// DELETE - by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLeave(@PathVariable int id) {
		Leave leave = leaveRepo.findById(id).orElse(null);  // fixed
		if (leave == null) {
			return ResponseEntity.notFound().build();
		}
		leaveRepo.deleteById(id);  // fixed
		return ResponseEntity.ok("Leave deleted Successfully");
	}
	
	// Exception handling
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleInvalidInput(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
