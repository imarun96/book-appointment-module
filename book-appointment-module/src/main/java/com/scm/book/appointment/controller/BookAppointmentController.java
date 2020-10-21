package com.scm.book.appointment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scm.book.appointment.dto.BookAppointmentDTO;
import com.scm.book.appointment.entity.BookAppointmentEntity;
import com.scm.book.appointment.service.BookAppointmentService;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookAppointmentController {
	private BookAppointmentService bookAppointmentService;

	public BookAppointmentController(BookAppointmentService bookAppointmentService) {
		this.bookAppointmentService = bookAppointmentService;
	}

	Logger logger = LoggerFactory.getLogger(BookAppointmentController.class);

	/*
	 * Param - BookAppointment To create a new appointment
	 */

	@RequestMapping(path = "/appointment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookAppointmentEntity addAppointment(@RequestBody BookAppointmentEntity bookAppointment) {
		return bookAppointmentService.insertPatientDetails(bookAppointment);
	}

	/*
	 * Lists all the appointments
	 */

	@GetMapping("/appointment")
	public List<BookAppointmentEntity> getAllAppointments() {
		return bookAppointmentService.getAll();
	}

	/*
	 * Param - ID Get specific appointment
	 */

	@GetMapping("/appointment/{id}")
	public BookAppointmentEntity getSingleRecord(@PathVariable(name = "id") Long id) {
		return bookAppointmentService.getSinglePatientRecord(id);
	}

	@GetMapping("/timeSlots/{date}/{docType}")
	public List<String> getAvailableTimeSlots(@PathVariable(name = "date") String date,
			@PathVariable(name = "docType") String docType) {
		return bookAppointmentService.getAvailableSlots(date, docType);
	}

	/*
	 * Param - ID Delete a specific appointment
	 */

	@DeleteMapping("/appointment/{id}")
	public String deleteAppointment(@PathVariable(name = "id") Long id) {
		return bookAppointmentService.deleteById(id);
	}
}