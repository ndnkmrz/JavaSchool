package com.gamershop.admin.user;

import com.gamershop.shared.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserService {
    private UserRepository repo;
    public UserService(UserRepository _repo){
        this.repo = _repo;
    }

    public Iterable<User> listAll(){
        return repo.findAll();
    }
}
