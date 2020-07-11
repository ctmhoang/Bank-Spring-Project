package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.domain.security.UserRole;
import com.ctmhoang.userfront.service.IRoleService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    //Spring known that used ThymeLeaf temp engine -> return automatically recognize as  template name
    @RequestMapping("/")
    public String home()
    {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model)
    {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model)
    {
        if (userService.isUserExists(user.getUsrName(), user.getEmail()))
        {

            if (userService.checkEmailExists(user.getEmail()))
                model.addAttribute("emailExists", true);

            if (userService.checkUserNameExists(user.getUsrName()))
                model.addAttribute("usernameExists", true);

            return "signup";
        }
        else
        {
            if(roleService.getUserRole() == null) roleService.createBasicRoles();
            userService.create(user, Set.of(new UserRole(user, roleService.getUserRole())));
            return "redirect:/";
        }
    }
}
