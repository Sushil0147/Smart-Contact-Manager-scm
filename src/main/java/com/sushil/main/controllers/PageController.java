package com.sushil.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sushil.main.DTO.UserForm;
import com.sushil.main.entities.User;
import com.sushil.main.helper.Message;
import com.sushil.main.helper.MessageType;
import com.sushil.main.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){

        model.addAttribute("name", "Sushi Kumar");
        model.addAttribute("Youtubechannel", "Sushi0147");
        model.addAttribute("gitrepo", "https://github.com/Sushil0147/");
        return "home";
    }

    // about page 
    @RequestMapping("/about")
    public String about(){
        return "about";
    } 

    // services page 
    @RequestMapping("/services")
    public String services(){
        return "services";
    } 

    // contact us page 
     @RequestMapping("/contact")
    public String contact(){
        return "contact";
    } 

    //login page
     @RequestMapping("/login")
    public String login(){
        return "login";
    } 

    // register page
     @RequestMapping("/register")
    public String register(Model model){

        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);

        return "register";
    } 

    @PostMapping("/do-register")
    public String submitFormData(@Valid @ModelAttribute UserForm userForm,BindingResult result ,HttpSession session){
        
        //validation
        if (result.hasErrors()) {
            return "register";
        }

        User user = new User();

        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhone(userForm.getPhone());
        user.setProfilePic("https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg");

        userService.saveUser(user);

        Message message  = Message.builder().content("Registration Successful").type(MessageType.blue).build();

        session.setAttribute("message", message);

        return "redirect:/register";
    }
    
}
