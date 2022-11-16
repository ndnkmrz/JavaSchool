package com.gamershop.admin.user;

import com.gamershop.shared.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }
}
