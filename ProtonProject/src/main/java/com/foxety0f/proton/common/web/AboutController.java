package com.foxety0f.proton.common.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.foxety0f.proton.common.abstracts.AbstractController;

public class AboutController extends AbstractController{

	@RequestMapping("/about")
	public String indexAbout() {
		return "about";
	}
	
}
