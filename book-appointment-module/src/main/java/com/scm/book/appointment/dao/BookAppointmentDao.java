package com.scm.book.appointment.dao;

import java.util.List;

import com.scm.book.appointment.dto.BookAppointmentDTO;

public interface BookAppointmentDao {

	BookAppointmentDTO insertAppointment(BookAppointmentDTO bookAppointment);

	List<BookAppointmentDTO> getAll();

	BookAppointmentDTO singleAppointment(Long id);

	String deleteAppointment(Long id);

	List<String> getTimeSlots(String date, String docType);
}