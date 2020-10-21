package com.scm.book.appointment.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.book.appointment.dao.BookAppointmentDao;
import com.scm.book.appointment.dto.BookAppointmentDTO;
import com.scm.book.appointment.entity.BookAppointmentEntity;

@Service
public class BookAppointmentServiceImpl implements BookAppointmentService {
	@Autowired
	BookAppointmentDao bookAppointmentDao;

	private Calendar cal = Calendar.getInstance();

	@Override
	public BookAppointmentEntity insertPatientDetails(BookAppointmentEntity bookAppointment) {
		BookAppointmentDTO dto = new BookAppointmentDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(bookAppointment, dto);
		dto.setInputDateTime(new Timestamp(cal.getTimeInMillis()));
		dto.setLastUpdateDateTime(new Timestamp(cal.getTimeInMillis()));
		modelMapper.map(bookAppointmentDao.insertAppointment(dto), bookAppointment);
		return bookAppointment;
	}

	@Override
	public List<BookAppointmentEntity> getAll() {
		ModelMapper modelMapper = new ModelMapper();
		return Arrays.asList(modelMapper.map(bookAppointmentDao.getAll(), BookAppointmentEntity[].class));
	}

	@Override
	public BookAppointmentEntity getSinglePatientRecord(Long id) {
		BookAppointmentEntity entity = new BookAppointmentEntity();
		ModelMapper modelMapper = new ModelMapper();
		BookAppointmentDTO dto = bookAppointmentDao.singleAppointment(id);
		if (Objects.nonNull(dto)) {
			modelMapper.map(dto, entity);
		}
		return entity;
	}

	@Override
	public String deleteById(Long id) {
		return bookAppointmentDao.deleteAppointment(id);
	}

	@Override
	public List<String> getAvailableSlots(String date, String docType) {
		List<String> tempList = bookAppointmentDao.getTimeSlots(date, docType);
		if (!tempList.isEmpty()) {
			List<String> tmpList = Stream
					.of("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "14:00",
							"14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00")
					.collect(Collectors.toList());
			tmpList.removeAll(tempList);
			return tmpList;
		} else {
			return Stream
					.of("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "14:00",
							"14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00")
					.collect(Collectors.toList());
		}
	}
}