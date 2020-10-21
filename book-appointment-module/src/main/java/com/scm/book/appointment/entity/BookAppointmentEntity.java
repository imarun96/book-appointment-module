package com.scm.book.appointment.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookAppointmentEntity {
	private String docType;
	private String date;
	private String phnNumber;
	private String name;
	private String timeSlot;
}