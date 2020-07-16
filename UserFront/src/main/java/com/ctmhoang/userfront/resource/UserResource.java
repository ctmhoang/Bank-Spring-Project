package com.ctmhoang.userfront.resource;

import com.ctmhoang.userfront.domain.PrimaryTransaction;
import com.ctmhoang.userfront.domain.SavingsTransaction;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
//@PreAuthorize("hasRole('ADMIN')")
public class UserResource {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> userList() {
        return userService.fetchAll();
    }

    @RequestMapping(value = "/user/primary/transaction", method = RequestMethod.GET)
    public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("username") String username) {
        return transactionService.findPrimaryTransactions(username);
    }

    @RequestMapping(value = "/user/savings/transaction", method = RequestMethod.GET)
    public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username) {
        return transactionService.findSavingsTransactions(username);
    }

    @GetMapping("/user/{username}/enable")
    public void enableUser(@PathVariable("username") String username) {
        userService.enable(username);
    }

    @GetMapping("/user/{username}/disable")
    public void disableUser(@PathVariable("username") String username) {
        userService.disable(username);
    }
}
