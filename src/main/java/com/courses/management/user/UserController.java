package com.courses.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/showUsers")
    public String getAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "show_users";
    }

    @GetMapping(path = "/get")
    public String getUserById(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user_details";
    }

    @GetMapping(path = "/findPage")
    public String showFindUserPage() {
        return "find_user";
    }

    @GetMapping(path = "/find")
    public String findUser(@RequestParam("email") String email, Model model) {
        User user = null;
        try {
            user = userService.getUser(email);
        } catch (UserNotExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "find_user";
        }
        model.addAttribute("user", user);
        return "user_details";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registerUser(@ModelAttribute("userForm") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.registerUser(user);
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("message", "An account for that username already exists.");
            return "registration";
        }

        return "redirect:/login";
    }

    @ModelAttribute("userForm")
    public User getDefaultUser() {
        return new User();
    }
}
