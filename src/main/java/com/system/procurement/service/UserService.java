package com.system.procurement.service;

import com.system.procurement.dto.UserProfileDto;
import com.system.procurement.dto.UserValidationResponse;
import com.system.procurement.entity.UserProfile;

public interface UserService {
    UserProfile getUserProfile(Long id);
    UserProfile updateUserProfile(UserProfile userProfile);
    void deleteUser(Long id);
    UserProfileDto getUserByUsername(String username);
    UserProfileDto getUserByEmail(String email);
    UserValidationResponse validateUser(Long userId);

}
