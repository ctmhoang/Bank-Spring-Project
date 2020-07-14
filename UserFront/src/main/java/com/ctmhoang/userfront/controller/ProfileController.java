package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileController
{
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Principal principal, Model model)
    {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePOST(@ModelAttribute("user") User newUser, Model model)
    {
        User user = userService.findByUserName(newUser.getUsrName());

        user.setUsrName(newUser.getUsrName());
        user.setFstName(newUser.getFstName());
        user.setLstName(newUser.getLstName());
        user.setPhone(newUser.getPhone());
        user.setEmail(newUser.getEmail());

        model.addAttribute("user", user);
        userService.save(user);

        return "profile";
    }


}
