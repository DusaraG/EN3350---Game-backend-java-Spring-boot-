package com.example.EnergycorpBackend.dto;

import com.example.EnergycorpBackend.controller.UnityLoginController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtToken {
    private String jwt;

    public JwtToken() {
        this.jwt = UnityLoginController.currentPlayerJwt;
    }
}
