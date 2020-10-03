package com.foxety0f.proton.modules.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxety0f.proton.modules.reports.domain.MetaColumnDict;

@RestController
public class RestTestController {

	@RequestMapping({ "/rest/qweqew" })
	public String firstPage() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/rest/testTransform")
	public void get(@RequestParam(value = "map", required = false) MetaColumnDict map) {
		System.err.println(map.toString());
	}
}
