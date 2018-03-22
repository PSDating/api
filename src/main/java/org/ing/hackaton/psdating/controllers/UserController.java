package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.util.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"35.205.253.173:8080", "localhost:8080"})
@RequestMapping("/user")
public class UserController {

    @RequestMapping(path="/register", method= RequestMethod.POST)
    public String register(@RequestParam final String username, @RequestParam final String password) {
        Logger.log("User registered succesfully with username: " + username + ", password: " + password);
        return "User registered succesfully!";
    }

    @RequestMapping("/login")
    public String login(@RequestParam final String username, @RequestParam final String password) {
        Logger.log("User logged in succesfully with username: " + username + ", password: " + password);
        return "login";
    }
}
