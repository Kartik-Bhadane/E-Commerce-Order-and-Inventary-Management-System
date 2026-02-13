package com.example.E_CommerceOrder.service;

import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;





public interface UserService {

    void register(RegisterRequestdto request);

    AuthResponsedto login(LoginRequestdto request);
    
}