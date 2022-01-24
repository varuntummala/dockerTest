package com.springBoot.userDetails.service;

import com.springBoot.userDetails.exception.BadUserDetailsException;
import com.springBoot.userDetails.model.User;
import com.springBoot.userDetails.repository.UserRepository;
import com.springBoot.userDetails.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private UserRepository userRepository1;
    public Page<User> retrieveAllUsers(int offset, int pageSize, String field){
//        return userRepository.retrieveAllUsers();
        return userRepository1.findAll(PageRequest.of(offset,pageSize).withSort( Sort.by(Sort.Direction.DESC,field)));
    }

    public User getUserById(String id){
        return userRepository1.getById(id);
    }
    public void addUser(User user) throws BadUserDetailsException {
//        userRepository.addUser(user);
            userRepository1.save(user);
    }

    public void updateUser(User user){userRepository.updateUser(user);}

    public void deleteUser(User user){userRepository.deleteUser(user);}

    public List<User> getOnlineUsers(String onlineStatus){
        return userRepository.getOnlineUsers(onlineStatus);
    }
}
