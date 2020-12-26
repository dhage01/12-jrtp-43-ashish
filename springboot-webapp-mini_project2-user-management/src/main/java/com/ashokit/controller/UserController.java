package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.User;
import com.ashokit.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping(value = "/saveForm", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> saveRegForm(@RequestBody User user) {
		boolean saveUser = userService.saveUser(user);
		if (saveUser == true) {

			String succMsg = "the signup is complteted succeesfully please check your email to unlock your acc";
		} else {
			String errMsg = "the signup is not completed ";
		}

		return new ResponseEntity<String>("succMsg,errMsg", HttpStatus.CREATED);

	}

	@PostMapping(value = "/unlockAcc", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> unlockeAcc(@RequestBody String email, String tempPwd, String confirmPwd) {
		boolean tempPwdValid = userService.isTempPwdValid(email, tempPwd);
		if (tempPwdValid == true) {
			boolean unlockAccount = userService.unlockAccount(email, confirmPwd);
			if (unlockAccount == true) {
				String succMsg = "your acc is unlocked please proceed with login ";
			}
		} else {
			String errMsg = "your tempPwd is wrong";
		}
		return new ResponseEntity<String>("succMsg,errMsg", HttpStatus.OK);

	}

}
