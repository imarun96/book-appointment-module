package com.scm.book.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scm.book.appointment.entity.BookAppointmentEntity;

@Service
public interface BookAppointmentService {

	BookAppointmentEntity insertPatientDetails(BookAppointmentEntity bookAppointment);

	List<BookAppointmentEntity> getAll();

	BookAppointmentEntity getSinglePatientRecord(Long id);

	String deleteById(Long id);

	List<String> getAvailableSlots(String date, String docType);
}