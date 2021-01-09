package com.foxety0f.proton.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.modules.education.domain.TestApi;
import com.foxety0f.proton.modules.education.service.IEducationService;

@Controller
public class EducationController extends AbstractController {

    @Autowired
    private IEducationService educationService;

    @RequestMapping("/education")
    public String index(Model model, Principal principal) {
	if (principal != null) {
	    model.addAttribute("testApi", educationService.getTestApi());
	    model.addAttribute("tempTestData", educationService.getTempTestData());
	    educationService.setCnt(educationService.getCnt() + 1);
	    model.addAttribute("empCnt", educationService.getCnt());
	    
	}

	return "education.html";
    }

    @RequestMapping("/education/setTestApi")
    public ResponseEntity<?> setTestApi(@RequestParam(value = "id", required = false) Integer id,
	    @RequestParam(value = "name", required = false) String name,
	    @RequestParam(value = "isActual", required = false) Boolean isActual, Principal principal) {

	educationService.setTestApi(id, name, null, isActual);

	if (id == 999)
	    new ResponseEntity<String>("BAD", HttpStatus.FORBIDDEN);

	return new ResponseEntity<List<TestApi>>(educationService.getTestApi(), HttpStatus.OK);
    }

    @RequestMapping("/education/getTestApi")
    public ResponseEntity<?> getTestApi(Principal principal) {
	if (principal != null) {
	    return new ResponseEntity<List<TestApi>>(educationService.getTestApi(), HttpStatus.OK);
	}

	return new ResponseEntity<String>("AUTH", HttpStatus.UNAUTHORIZED);
    }

}
