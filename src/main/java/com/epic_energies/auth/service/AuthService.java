package com.epic_energies.auth.service;

import com.epic_energies.auth.payload.LoginDto;
import com.epic_energies.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
