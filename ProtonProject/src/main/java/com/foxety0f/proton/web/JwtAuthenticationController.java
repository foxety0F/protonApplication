package com.foxety0f.proton.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.common.user.UserDetailsServiceImpl;
import com.foxety0f.proton.utils.JwtTokenUtils;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager am;

	@Autowired
	private JwtTokenUtils jtu;

	@Autowired
	private UserDetailsServiceImpl udsi;

	@RequestMapping(value = "/proton/restauth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception{
		authenticate(request.getUsername(), request.getPassword());
		
		final UserDetailsProton user = (UserDetailsProton) udsi.loadUserByUsername(request.getUsername());
		
		final String token = jtu.generateToken(user);
		
		return ResponseEntity.ok(new JwtInfo(new Date(), token, jtu.getExpirationDateFromToken(token)));
	}

	public void authenticate(String username, String password) throws Exception{
		try {
			am.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("DISABLED", e);
		}catch(BadCredentialsException e) {
			throw new Exception("USERNAME_PASSWORD_INVALID", e);
		}
	}
	
}
