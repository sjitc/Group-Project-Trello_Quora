package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthTokenEntity;
import com.upgrad.quora.service.exception.SignOutRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SignOutService {
    @Autowired
    private UserDao userDao;

    public UserAuthTokenEntity verifyAuth(final String authToken) throws SignOutRestrictedException {
        UserAuthTokenEntity userAuthTokenEntity = userDao.getUserByAuthtoken(authToken);

        if (userAuthTokenEntity == null) {
            throw new SignOutRestrictedException("SGR-001", "User is not Signed in");
        }

        userAuthTokenEntity.setLogoutAt(ZonedDateTime.now());
        return userAuthTokenEntity;
    }
}