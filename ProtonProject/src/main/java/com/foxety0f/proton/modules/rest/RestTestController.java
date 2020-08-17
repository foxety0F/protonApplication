package com.foxety0f.proton.modules.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	@RequestMapping({ "/rest/qweqew" })
	public String firstPage() {
		return "Hello World";
	}
}
