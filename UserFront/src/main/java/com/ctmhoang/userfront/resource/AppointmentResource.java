package com.ctmhoang.userfront.resource;

import java.util.List;

import com.ctmhoang.userfront.domain.Appointment;
import com.ctmhoang.userfront.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/appointment")
@PreAuthorize("hasRole('ADMIN')")
public class AppointmentResource
{

    @Autowired
    private IAppointmentService appointmentService;

    @RequestMapping("/all")
    public List<Appointment> findAppointmentList()
    {
        return appointmentService.fetchAll();
    }

    @GetMapping("/{id}/confirm")
    public void confirmAppointment(@PathVariable("id") Long id)
    {
        appointmentService.confirmAppointment(id);
    }
}
