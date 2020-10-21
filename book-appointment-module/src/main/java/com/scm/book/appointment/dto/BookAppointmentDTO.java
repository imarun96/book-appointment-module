package com.scm.book.appointment.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "bookappointment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookAppointmentDTO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private long booking_id;
	@Column(name = "docType")
	private String docType;
	@Column(name = "date")
	private String date;
	@Column(name = "phoneNumber")
	private String phnNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "timeSlot")
	private String timeSlot;
	@Column(name = "inputUserId")
	private String inputUserId;
	@Column(name = "lastUpdateUserId")
	private String lastUpdateUserId;
	@Column(name = "inputDateTime")
	private Timestamp inputDateTime;
	@Column(name = "lastUpdateDateTime")
	private Timestamp lastUpdateDateTime;
}
