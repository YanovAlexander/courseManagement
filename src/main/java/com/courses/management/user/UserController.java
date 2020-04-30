package com.courses.management.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private Users users;

    public void setUsers(Users users) {
        this.users = users;
    }

    @GetMapping(path = "/showUsers")
    public String getAllUsers(Model model) {
        List<User> userList = users.getAllUsers();
        model.addAttribute("users", userList);
        return "show_users";
    }

    @GetMapping(path = "/get")
    public String getUserById(@RequestParam("id") Integer id, Model model) {
        User user = users.getUser(id);
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
            user = users.getUser(email);
        } catch (UserNotExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "find_user";
        }
        model.addAttribute("user", user);
        return "user_details";
    }
}