package com.aathif.its.task.service.serviceImpl;

import com.aathif.its.task.dto.LoginDTO;
import com.aathif.its.task.dto.RequestMetaDTO;
import com.aathif.its.task.dto.UpdateUserDTO;
import com.aathif.its.task.dto.UserDTO;
import com.aathif.its.task.model.User;
import com.aathif.its.task.repository.UserRepository;
import com.aathif.its.task.service.UserService;
import com.aathif.its.task.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RequestMetaDTO requestMetaDTO;

    @Override
    public ResponseEntity<?> register(UserDTO userDTO) {
        if (userDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name Not Found");
        } else if (userDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Not Found");
        } else if (userDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile Not Found");
        } else if (userDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Email exists");
        } else {

            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setMobile(userDTO.getMobile());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");

        }
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        if (loginDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Not Found");
        } else if (loginDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else {

            User user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email or Password");
            }
            if (user.isActive()) {
                String accessToken = jwtUtil.generateAccessToken(user);
                Map<String, String> data = new HashMap<>();
                data.put("accessToken", accessToken);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your Account Deleted");
            }


        }
    }

    @Override
    public ResponseEntity<?> update(UpdateUserDTO updateUserDTO) {
        if (updateUserDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name Not Found");
        } else if (updateUserDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile Not Found");
        } else if (updateUserDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else {
            Optional<User> optionalUser = userRepository.findById(requestMetaDTO.getId());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
            }
            User user = optionalUser.get();
            user.setName(updateUserDTO.getName());
            user.setMobile(updateUserDTO.getMobile());
            user.setPassword(updateUserDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Update Successfully");

        }
    }

    @Override
    public ResponseEntity<?> delete() {
        Optional<User> optionalUser = userRepository.findById(requestMetaDTO.getId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
        }
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Delete Successfully");
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
