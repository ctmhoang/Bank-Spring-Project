package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {
  Appointment findById(String id);
}
