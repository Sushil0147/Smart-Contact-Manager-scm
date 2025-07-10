package com.sushil.main.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sushil.main.entities.User;
import com.sushil.main.exceptions.UserNotFoundException;
import com.sushil.main.repositories.UserRepository;
import com.sushil.main.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // @Autowired
	// private JwtService jwtService;

    @Override
    public User saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("ROLE_USER"));
		
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("user not found with id :" + id));
        return user!=null ? user : null;
    }

    @Override
    public User updateUser(User user) {

       User userExist = getUserById(user.getId());

       if(userExist!=null){
            userExist.setName(user.getName());
            userExist.setEmail(user.getEmail());
            userExist.setPassword(user.getPassword());
            userExist.setAbout(user.getAbout());
            userExist.setPhone(user.getPhone());

            return userRepository.save(userExist);
       }
       else{
            return null;
       }
    }

    @Override
    public void deleteUser(User user) {
        User userExist = getUserById(user.getId());
        if(userExist!=null){
            userRepository.delete(user);
        }
    }

    @Override
    public User isUserExistByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

}
