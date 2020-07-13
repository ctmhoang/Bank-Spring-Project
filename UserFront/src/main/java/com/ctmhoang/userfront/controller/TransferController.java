package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.Recipient;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {
  @Autowired private ITransactionService transactionService;
  @Autowired private IUserService userService;

  @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
  public String betweenAccount(Model model) {
    model.addAttribute("transferFrom");
    model.addAttribute("transferTo");
    model.addAttribute("amount");
    return "betweenAccounts";
  }

  @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
  public String betweenAccountPOST(
      @ModelAttribute("transferFrom") String from,
      @ModelAttribute("transferTo") String to,
      @ModelAttribute("amount") String amount,
      Principal principal)
      throws Exception {
    User user = userService.findByUserName(principal.getName());
    var primaryAccount = user.getPrimAcc();
    var savingsAccount = user.getSaveAcc();
    transactionService.betweenAccountsTransfer(from, to, primaryAccount, savingsAccount, amount);
    return "redirect:/account";
  }

  @RequestMapping(value = "/recipient", method = RequestMethod.GET)
  public String recipient(Model model, Principal principal) {
    List<Recipient> repList = transactionService.findRecipients(principal);
    var tmp = new Recipient();
    model.addAttribute("recipientList", repList);
    model.addAttribute("recipient", tmp);
    return "recipient";
  }

  @RequestMapping(value = "/recipient/save", method = RequestMethod.POST)
  public String recipientPOST(
      @ModelAttribute("recipient") Recipient recipient, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    recipient.setUsr(user);
    transactionService.saveRecipient(recipient);
    return "redirect:/transfer/recipient";
  }

  @RequestMapping(value = "/recipient/edit", method = RequestMethod.GET)
  public String recipientEdit(
      @RequestParam("recipientName") String name, Model model, Principal principal) {
    Recipient recipient = transactionService.findRecipientByName(name);
    List<Recipient> recipientList = transactionService.findRecipients(principal);

    model.addAttribute("recipientList", recipientList);
    model.addAttribute("recipient", recipient);

    return "recipient";
  }

  @RequestMapping(value = "recipient/delete", method = RequestMethod.GET)
  public String recipientDel(
      @RequestParam("recipientName") String name, Model model, Principal principal) {
    transactionService.deleteRecipientByName(name);
    List<Recipient> recipientList = transactionService.findRecipients(principal);
    var recipient = new Recipient();

    model.addAttribute("recipientList", recipientList);
    model.addAttribute("recipient", recipient);

    return "recipient";
  }
}
