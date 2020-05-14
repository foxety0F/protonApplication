package com.foxety0f.proton.modules.distribution.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.modules.distribution.domain.DistributionStatus;
import com.foxety0f.proton.modules.distribution.domain.DistributionTime;

@Controller
public class DistributionController extends AbstractController{

	@RequestMapping(value = "/distribution")
	public String distributionInit() {
		return "distribution/indexDistribution";
	}
	
	@RequestMapping(value = "/distribution/putNewDistribution")
	public ResponseEntity<String> putNewDistribution(@RequestParam(required = true, value = "id_employee") Integer id_employee,
			@RequestParam(required = true, value = "date") Date date,
			@RequestParam(required = true, value = "id_status") Integer idStatus,
			@RequestParam(required = true, value = "id_time") Integer idTime,
			@RequestParam(required = false, value = "description") String description){
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/distribution/massUpdateDistribution")
	public ResponseEntity<String> massUpdateDistribution(@RequestParam(required = true, value = "json") String json,
			@RequestParam(required = false, value = "description") String desription){
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/distribution/distributionStatuses")
	public ResponseEntity<DistributionStatus> getStatuses(){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/distribution/distributionTimes")
	public ResponseEntity<DistributionTime> getTime(){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
