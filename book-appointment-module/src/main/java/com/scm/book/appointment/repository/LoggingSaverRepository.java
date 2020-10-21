package com.scm.book.appointment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.scm.book.appointment.dto.LoggingSaver;

@Repository
public class LoggingSaverRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public LoggingSaver addLog(LoggingSaver loggingSaver) {
		mapper.save(loggingSaver);
		return loggingSaver;
	}
}