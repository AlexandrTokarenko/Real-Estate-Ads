package com.example.realestateads.controller.auth;


import com.example.realestateads.dto.UserRestoreDTO;
import com.example.realestateads.entity.User;
import com.example.realestateads.service.dataServices.UserService;
import com.example.realestateads.service.email.EmailServiceImpl;
import com.example.realestateads.service.email.PasswordResetUtil;
import com.example.realestateads.service.email.TimeChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final PasswordResetUtil passwordResetUtil;
    private final TimeChecker timeChecker;

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {

        return "user/auth/forgot-password";
    }

    @PostMapping("/restore-password")
    public String restorePassword(Model model, @RequestParam String username) {

        User user = userService.findById(username);

        if (user != null) {
            emailService.sendActivationCodeForRestorePassword(user.getEmail());
        } else {
            model.addAttribute("error","Неправильна електронна адреса");
            return "user/auth/forgot-password";
        }

        model.addAttribute("userEmail",user.getEmail());
        return "user/auth/restore-password";
    }

    @PostMapping("/register/check-activation-code/{userEmail}")
    public String checkActivationCode(@PathVariable String userEmail, Model model, @RequestParam("activation_code") String activationCode,
                                      RedirectAttributes redirectAttributes) {

        User user = userService.findById(userEmail);
        if (user != null) {
            if (user.getActivationCode().equals(activationCode)) {
                if (timeChecker.checkTime(user.getEffectiveTime())) {
                    userService.updateVerifyEmail(user,true);
                    return "user/auth/login";
                } else {
                    model.addAttribute("userEmail",user.getEmail());
                    model.addAttribute("error","Термін дії коду закінчився");
                    return "user/auth/register-email-activation";
                }
            } else {
                model.addAttribute("userEmail",user.getEmail());
                model.addAttribute("error","Неправильний код активації");
                return "user/auth/register-email-activation";
            }
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/check-restore-code/{userEmail}")
    public String checkRestoreCode(Model model, @RequestParam("activation_code") String activationCode, @PathVariable String userEmail, RedirectAttributes redirectAttributes) {

        User user = userService.findById(userEmail);

        if (user != null) {

            if (user.getActivationCode().equals(activationCode)) {
                if (timeChecker.checkTime(user.getEffectiveTime())) {
                    model.addAttribute("userDTO",new UserRestoreDTO(userEmail,""));
                    return "user/auth/new-password";
                } else {
                    model.addAttribute("userEmail",user.getEmail());
                    model.addAttribute("error","Термін дії коду закінчився");
                    return "user/auth/restore-password";
                }
            } else {
                model.addAttribute("userEmail",user.getEmail());
                model.addAttribute("error","Неправильний код активації");
                return "user/auth/restore-password";
            }
        } else {
            return "redirect:/auth/forgot-password";
        }

    }

    @PostMapping("/update-password")
    public String updatePassword(@Validated @ModelAttribute("userDTO") UserRestoreDTO user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/auth/new-password";
        }

        userService.updatePassword(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/send-email-again/{userEmail}")
    public String sendEmailAgain(@PathVariable String userEmail, Model model) {

        User user = userService.findById(userEmail);

        if (user != null) {
            emailService.sendActivationCodeForRestorePassword(userEmail);
        }
        model.addAttribute("userEmail",user.getEmail());
        return "user/auth/restore-password";
    }
}
