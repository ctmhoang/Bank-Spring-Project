package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.PrimaryTransaction;
import com.ctmhoang.userfront.domain.SavingsTransaction;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.IAccountService;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
  @Autowired private IUserService userService;

  @Autowired private IAccountService accountService;

  @Autowired private ITransactionService transactionService;

  @RequestMapping("/primaryAccount")
  public String primaryAcc(Model model, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    List<PrimaryTransaction> transactionList =
        transactionService.findPrimaryTransactions(principal.getName());
    model.addAttribute("primaryAccount", user.getPrimAcc());
    model.addAttribute("primaryTransactionList", transactionList);
    return "primaryAccount";
  }

  @RequestMapping("/savingsAccount")
  public String saveAcc(Model model, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    List<SavingsTransaction> transactionList =
        transactionService.findSavingsTransactions(principal.getName());
    model.addAttribute("savingsAccount", user.getSaveAcc());
    model.addAttribute("savingsTransactionList", transactionList);
    return "savingsAccount";
  }

  @RequestMapping(value = "/deposit", method = RequestMethod.GET)
  public String deposit(Model model) {
    model.addAttribute("accountType");
    model.addAttribute("amount");
    return "deposit";
  }

  @RequestMapping(value = "/deposit", method = RequestMethod.POST)
  public String depositPOST(
      @ModelAttribute("amount") String amount,
      @ModelAttribute("accountType") String type,
      Principal principal) {
    accountService.deposit(type, Double.parseDouble(amount), principal);
    return "redirect:/account";
  }

  @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
  public String withdraw(Model model) {
    model.addAttribute("accountType");
    model.addAttribute("amount");
    return "withdraw";
  }

  @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
  public String withdrawPOST(
      @ModelAttribute("amount") String amount,
      @ModelAttribute("accountType") String type,
      Principal principal) {
    accountService.withdraw(type, Double.parseDouble(amount), principal);
    return "redirect:/account";
  }
}
