package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/transfer")
public class TransferController
{
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/betweenAccounts",method = RequestMethod.GET)
    public String betweenAccount(Model model)
    {
        model.addAttribute("transferFrom");
        model.addAttribute("transferTo");
        model.addAttribute("amount");
        return "betweenAccounts";
    }

     @RequestMapping(value = "/betweenAccounts",method = RequestMethod.POST)
    public String betweenAccountPOST(@ModelAttribute("transferFrom") String from,
                                     @ModelAttribute("transferTo") String to,
                                     @ModelAttribute("amount") String amount,
                                     Principal principal) throws Exception
     {
        User user = userService.findByUserName(principal.getName());
        var primaryAccount = user.getPrimAcc();
        var savingsAccount = user.getSaveAcc();
        transactionService.betweenAccountsTransfer(from,to,primaryAccount,savingsAccount,amount);
        return "redirect:/account";
    }


}
