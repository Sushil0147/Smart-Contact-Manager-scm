package com.sushil.main.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 4,max = 12,message = "Minimum 5 character and maximum 12 is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5,max = 12,message = "Password must be of minimum 5 and maximum 12 character")
    private String password;

    @NotBlank(message = "Phone is required")
    @Size(min = 10,max=12,message = "Please enter valid phone number")
    private String phone;

    @NotBlank(message = "About is required")
    private String about;

}
