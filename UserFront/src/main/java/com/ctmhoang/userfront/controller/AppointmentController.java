package com.ctmhoang.userfront.controller;

import com.ctmhoang.userfront.domain.Appointment;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.IAppointmentService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/appointment")
public class AppointmentController
{
    @Autowired
    private IUserService userService;

    @Autowired
    private IAppointmentService appointmentService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String createAppointment(Model model)
    {
        var appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("dateString");
        return "appointment";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAppointmentPOST(@ModelAttribute("appointment") Appointment appointment,
                                        @ModelAttribute("dateString") String date,
                                        Model model,
                                        Principal principal) throws ParseException
    {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        var dateParsed = dateFormat.parse(date);

        appointment.setDate(dateParsed);

        User user = userService.findByUserName(principal.getName());
        appointment.setUsr(user);

        appointmentService.createAppointment(appointment);
        return "redirect:/account";
    }

}
