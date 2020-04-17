package com.boot.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class LogIn {
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String hello() {
		return "HelloWorld";
	}
}
