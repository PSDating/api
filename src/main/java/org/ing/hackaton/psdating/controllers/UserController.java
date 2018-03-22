package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.util.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public final class UserController {

    @RequestMapping(path="/register", method= RequestMethod.POST)
    public final String register(@RequestParam final String username, @RequestParam final String password) {
        Logger.log("User registered succesfully with username: " + username + ", password: " + password);
        return "User registered succesfully!";
    }

    @RequestMapping("/login")
    public final String login(@RequestParam final String username, @RequestParam final String password) {
        Logger.log("User logged in succesfully with username: " + username + ", password: " + password);
        return "login";
    }
}
