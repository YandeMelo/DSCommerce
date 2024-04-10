package com.yandemelo.dscommercePGAdmin.services.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yandemelo.dscommercePGAdmin.entities.authEntities.User;
import com.yandemelo.dscommercePGAdmin.services.exceptions.ForbiddenException;

@Service
public class AuthService {

    @Autowired
    private AuthorizationService UserService;

    public void validateSelfOrAdmin(Long userId){
        User me = UserService.authenticated();
        if (!me.hasRole("ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    }

}
