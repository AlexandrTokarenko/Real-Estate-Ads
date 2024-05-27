package com.example.realestateads.controller.auth;

import com.example.realestateads.dto.UserRegisterDTO;
import com.example.realestateads.entity.User;
import com.example.realestateads.exception.UserAlreadyExistException;
import com.example.realestateads.repository.UserRepository;
import com.example.realestateads.service.dataServices.UserService;
import com.example.realestateads.service.email.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @GetMapping("/login")
    public String login( Model model) {
//        @RequestParam(value = "error",defaultValue = "false") boolean loginError,
   //     model.addAttribute("request", new AuthenticationRequest());
        return "user/auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO user, Model model) {

        model.addAttribute("user",user);

        return "user/auth/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") UserRegisterDTO user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/auth/register";
        }

        try{
            userService.save(User.fromUserDTO(user));
        } catch (UserAlreadyExistException uaeEx) {
            return "user/auth/register";
        }

        emailService.sendActivationCodeForVerifyEmail(user.getEmail());
        model.addAttribute("userEmail",user.getEmail());
        return "user/auth/register-email-activation";
    }
}
