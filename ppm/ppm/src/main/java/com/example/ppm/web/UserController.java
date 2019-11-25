package com.example.ppm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppm.domain.User;
import com.example.ppm.payload.JWTLoginSuccessResponse;
import com.example.ppm.payload.LoginRequest;
import com.example.ppm.repositories.UserRepository;
import com.example.ppm.security.JwtTokenProvider;
import com.example.ppm.services.MapValidationErrorService;
import com.example.ppm.services.UserService;
import com.example.ppm.validator.UserValidator;
import  static com.example.ppm.security.SecurityConstants.TOKEN_PREFIX;
//import static org.junit.Assert.assertThat;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
   private PasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    	 ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
         if(errorMap != null)return errorMap;
         
         UsernamePasswordAuthenticationToken authReq
         = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(),null);
//    Authentication auth =    authenticationManager.authenticate(authReq);
//        SecurityContext sc = SecurityContextHolder.getContext();
         
         Authentication authentication = authenticationManager.authenticate(authReq);
         
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = TOKEN_PREFIX  + tokenProvider.generateToken(authentication) ;
         return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
//    return ResponseEntity.ok().body(authentication);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
    	userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}