package com.aathif.its.task.service;

import com.aathif.its.task.dto.LoginDTO;
import com.aathif.its.task.dto.UpdateUserDTO;
import com.aathif.its.task.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseEntity<?> register(UserDTO userDTO);

    public ResponseEntity<?> login(LoginDTO loginDTO);

    public ResponseEntity<?> update(UpdateUserDTO updateUserDTO);

    public ResponseEntity<?> delete();

    public ResponseEntity<?> getAllUser();
}
