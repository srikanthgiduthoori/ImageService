package com.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sync.model.User;
import com.sync.service.UserService;
import com.sync.vo.JwtRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User", description = "APIs for User related operations")
public class UserController {

	@Autowired
	private UserService userDetailsService;
	
	@ApiOperation(value = "Authenticate user by username and password", response = Object.class)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		return ResponseEntity.ok(userDetailsService.createAuthenticationToken(authenticationRequest));
	}
	
	
	@ApiOperation(value = "Register user to the system with basic details like username,firstname, lastname, email, gender", response = Object.class)
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	
	
	@ApiOperation(value = "Provides the user basic details as well as the images uploded by him", response = Object.class)
	 @GetMapping("/profile")
	 public ResponseEntity<?> getUserProfile(Authentication authentication) {
		 return ResponseEntity.ok(userDetailsService.findByUsername(authentication.getName()));
	 }


	

	



}
