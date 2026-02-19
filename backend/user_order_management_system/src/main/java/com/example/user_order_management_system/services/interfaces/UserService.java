package com.example.user_order_management_system.services.interfaces;



import com.example.user_order_management_system.DTO.PasswordChangeRequest;
import com.example.user_order_management_system.DTO.UserDTO;
import com.example.user_order_management_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO userDTO, Long id);
    void deleteUser(Long id);
    User findByEmail(String email);
    List<UserDTO> getAllUsers();
//    Optional<User> Login(String email, String password);
    UserDTO signUp(UserDTO userDTO);
    boolean changePassword(Long userId, PasswordChangeRequest passwordChangeRequest);
}