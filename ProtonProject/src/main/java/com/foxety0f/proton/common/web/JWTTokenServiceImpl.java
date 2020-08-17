package com.foxety0f.proton.common.web;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import com.foxety0f.proton.common.user.UserDetailsServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JWTTokenServiceImpl implements GetTokenService {
  @Autowired
  private UserDetailsServiceImpl us;
  
  @Override
  public TokenObject getToken(String username, String password) throws Exception{
    if(username == null || password == null)
      return null;
      
    UserDetailsProton user = (UserDetailsProton) us.loadUserByUsername(username);
    Map<String, Object> tokenData = new HashMap<>();
    
    if(password.equals(user.getPassword())){
      tokenData.put("clientType", "user");
      tokenData.put("userId", user.getUserId().toString());
      tokenData.put("username", user.getUsername());
      tokenData.put("token_create_date", new Date().getTime());
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.YEAR, 100);
      tokenData.put("tolen_expiration_date", calendar.getTime());
      JwtBuilder jwtBuilder = Jwts.builder();
      jwtBuilder.setExpiration(calendar.getTime());
      jwtBuilder.setClaims(tokenData);
      String key = "I wanna know your name";
      String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
      return token;
    }else{
      throw new Exception("Authentication error");
    }
  }
}
