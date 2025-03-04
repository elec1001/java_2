package com.example.session.service;

import com.example.session.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User>  members=new ArrayList<>();

    public void addUser(User user){
        members.add(user);
    }

    public User getMembersById(String id){
        for(User user:members){
            if(user.getId().equals(id)){
                return user;
            }
        }
       return null;
    }


}
