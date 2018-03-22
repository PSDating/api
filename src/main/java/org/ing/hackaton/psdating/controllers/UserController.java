package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.domain.UsernamePassword;
import org.ing.hackaton.psdating.util.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public final class UserController {

    @RequestMapping(path="/register", method= RequestMethod.POST)
    public final String register(@RequestBody UsernamePassword usernamePassword) {
        Logger.log("User registered succesfully with username: " + usernamePassword.username + ", password: " + usernamePassword.password);
        return "User registered succesfully!";
    }

    @RequestMapping("/login")
    public final String login(@RequestBody UsernamePassword usernamePassword) {
        Logger.log("User logged in succesfully with username: " + usernamePassword.username + ", password: " + usernamePassword.password);
        return "login";
    }
}
