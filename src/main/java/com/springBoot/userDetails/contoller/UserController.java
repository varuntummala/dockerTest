package com.springBoot.userDetails.contoller;

import com.springBoot.userDetails.DTO.UserDTO;
import com.springBoot.userDetails.exception.BadUserDetailsException;
import com.springBoot.userDetails.model.Organization;
import com.springBoot.userDetails.model.User;
import com.springBoot.userDetails.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    RestTemplate restTemplate;
    @Value("${userDetails.appUrl:http://mydomain.com}")
            String url;
    @Value("${spring.application.fileName:abc}")
            String fileName;

//    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping("/users/sort/{field}/{offset}/{pageSize}")
    public Page<User> retrieveAllUsers(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field){
        log.info("Inside all users method");
        return userService.retrieveAllUsers(offset,pageSize, field);
    }

    @GetMapping("users/id/{id}")
    @CircuitBreaker(name="organizationMicroService",fallbackMethod = "fallBackGetUserById")
    public UserDTO getUserById(@PathVariable String id){
        User user =  userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        System.out.println("Config File Name is:"+fileName);
        Organization organization = restTemplate.getForObject(url+user.getOrg_Id(), Organization.class);
        userDTO.setOrganization(organization);
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreationDate(user.getCreationDate());
        userDTO.setOrg_Id(user.getOrg_Id());
        userDTO.setOnlineStatus(user.getOnlineStatus());
        return userDTO;
    }

//    @GetMapping("users/id/{id}")
    public UserDTO fallBackGetUserById(@PathVariable String id,Throwable t){
        Organization organization = new Organization();
        organization.setName(null);
        organization.setOrgId(null);

        User user =  userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        System.out.println("Config File Name is:"+fileName);

        userDTO.setOrganization(organization);
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreationDate(user.getCreationDate());
        userDTO.setOrg_Id(user.getOrg_Id());
        userDTO.setOnlineStatus(user.getOnlineStatus());
        return userDTO;
    }

    @PostMapping("/users")

    public ResponseEntity<String> addUser(@RequestBody User user) throws BadUserDetailsException {
        ResponseEntity<String> response = null;
        try {
            userService.addUser(user);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(Exception e){
            throw e;
        }
        return response;
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){userService.updateUser(user);}

    @DeleteMapping("/users")
    public void deleteUser(@RequestBody User user){userService.deleteUser(user);}

    @GetMapping("/users/{onlineStatus}")
    public List<User> getOnlineUsers(@PathVariable String onlineStatus){return userService.getOnlineUsers(onlineStatus);}

    @GetMapping("/users/")
    public ResponseEntity<List<User>> getOnlineusers(@RequestParam (value = "onlinestatus") String status)
    {
        List<User> users = userService.getOnlineUsers(status);
        ResponseEntity<List<User>> response = null;
        if(!users.isEmpty())
            response = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        else
            response = new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        return response;
    }

}


