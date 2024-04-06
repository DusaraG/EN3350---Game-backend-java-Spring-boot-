package com.example.EnergycorpBackend.controller;

import com.example.EnergycorpBackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnityLoginController {
    @Autowired
    PlayerService playerService;
    public static String currentPlayerJwt;

    @GetMapping("getCurrentPlayer")
    String getPlayer(){
        return currentPlayerJwt;
    }
}
