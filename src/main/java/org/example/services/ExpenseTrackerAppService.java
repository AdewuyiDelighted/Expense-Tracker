package org.example.services;

import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface ExpenseTrackerAppService {

    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

}
