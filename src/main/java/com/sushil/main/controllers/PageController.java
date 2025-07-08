package com.sushil.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
    public String register(){
        return "register";
    } 
    
}
