package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.AppointmentDao;
import com.ctmhoang.userfront.domain.Appointment;
import com.ctmhoang.userfront.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentService implements IAppointmentService {
  @Autowired private AppointmentDao appointmentDao;

  @Override
  public void createAppointment(Appointment appointment) {
    appointmentDao.save(appointment);
  }

  @Override
  public Appointment findAppointmentByID(Long id) {
    return appointmentDao.findById(id).orElse(null);
  }

  @Override
  public void confirmAppointment(Long id) {
    Appointment appointment = findAppointmentByID(id);
    appointment.setConfirmed(true);
    appointmentDao.save(appointment);
  }

  @Override
  public List<Appointment> fetchAll()
  {
    return StreamSupport.stream(appointmentDao.findAll().spliterator(),true).collect(Collectors.toList());
  }

}
