package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.Appointment;

import java.util.List;

public interface IAppointmentService {
  void createAppointment(Appointment appointment);

  Appointment findAppointmentByID(Long id);

  void confirmAppointment(Long id);

  List<Appointment> findAll();
}
