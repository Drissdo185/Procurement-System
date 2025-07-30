package com.system.procurement.service;

import com.system.procurement.dto.LoginResponse;
import com.system.procurement.dto.RequestLogin;
import com.system.procurement.dto.RequestRegistry;

public interface UserService {
    RequestRegistry register(RequestRegistry requestRegistry);
    LoginResponse login(RequestLogin requestLogin);
}
