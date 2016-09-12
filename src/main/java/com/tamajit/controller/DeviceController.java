package com.tamajit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeviceController {
	@RequestMapping(value="/dummyDevice")
	public String dummyDevice(){
		return "dummy_device";
	}
}
