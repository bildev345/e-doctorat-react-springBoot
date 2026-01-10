package org.example.doctoratrestapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public UserModel register(@RequestBody UserModel user){
        return userService.register(user);
    }
    @PostMapping("login")
    public String login(@RequestBody UserModel user){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user);
        }
        return "Failed";
    }
}
