package com.example.online_shop.security;

import com.example.online_shop.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),user.isActive(),true,true,true, AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
