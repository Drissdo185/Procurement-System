package com.system.procurement.service;

import com.system.procurement.dto.RequestRegistryDto;
import org.apache.coyote.Request;

public interface UserService {
    RequestRegistryDto register(RequestRegistryDto requestRegistryDto);
}
