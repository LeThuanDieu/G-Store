package com.example.gstore.service;
import com.example.gstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.gstore.controller.UserController;
import com.example.gstore.dto.requestDTO.LoginRequest;
import com.example.gstore.dto.requestDTO.RegisterRequest;
import com.example.gstore.dto.responseDTO.JwtResponse;
import com.example.gstore.model.User;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(RegisterRequest request){
        User user = new User();
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username đã tồn tại !");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Lỗi: Email đã tồn tại!");
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles("USER");

        return userRepository.save(user);

    }
    public User login(LoginRequest request ){
      User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng này !"));
      if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
        throw new RuntimeException("Sai mật khẩu !");
      }
      return user;
    }
}
