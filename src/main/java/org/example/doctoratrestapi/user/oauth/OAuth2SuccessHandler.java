package org.example.doctoratrestapi.user.oauth;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.doctoratrestapi.role.RoleModel;
import org.example.doctoratrestapi.role.RoleRepository;
import org.example.doctoratrestapi.user.JwtService;
import org.example.doctoratrestapi.user.UserModel;
import org.example.doctoratrestapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private JwtService jwtService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        if (email == null) {
            throw new IllegalStateException("Email not provided by OAuth2 provider");
        }
        Optional<UserModel> user = Optional.of(userRepo.findByUsername(email)
                .orElseThrow());

        String jwt = jwtService.generateToken(user.get());
        response.setContentType("application/json");
        response.getWriter().write("""
            {
                "token": "%s"
            }
            """.formatted(jwt));

    }
    // à discuter aprés
//    private UserModel createNewUser(OAuth2User oAuth2User){
//        UserModel user = new UserModel();
//        user.setUsername(oAuth2User.getAttribute("email"));
//
//        Optional<RoleModel> professorRole = Optional.ofNullable(roleRepo.findByName("ROLE_PROFESSOR").orElseThrow(() -> new IllegalStateException("Le ROLE_PROFESSEUR n'existe pas")));
//        professorRole.ifPresent(roleModel -> user.setRoles(Set.of(roleModel)));
//
//        return userRepo.save(user);
//    }

}
