package com.evertri.ecrf.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final TokenUtil tokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, TokenUtil tokenUtil) {
        this.authenticationService = authenticationService;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam("username") String username,
                                               @RequestParam("macAddress") String macAddress,
                                               @RequestParam("studyId") Long studyId) {
        if (authenticationService.isAuthorized(username, macAddress, studyId)) {
            String token = tokenUtil.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
