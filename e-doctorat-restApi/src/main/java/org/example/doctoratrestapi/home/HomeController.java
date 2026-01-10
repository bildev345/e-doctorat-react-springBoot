package org.example.doctoratrestapi.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String rootMethod(){
        return "Bonjour tout le monde";
    }

    @GetMapping("home")
    public String home(){
        return "Bonjour vous étes dans la page home";
    }

    @GetMapping("endpointProtege")
    public String protectedRoute(){
        return "Cette route est protégé";
    }
}
