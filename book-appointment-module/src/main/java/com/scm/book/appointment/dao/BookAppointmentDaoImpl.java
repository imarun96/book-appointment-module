package com.scm.book.appointment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import com.scm.book.appointment.Credential;
import com.scm.book.appointment.dto.BookAppointmentDTO;

@Repository
@EnableConfigurationProperties(Credential.class)

public class BookAppointmentDaoImpl implements BookAppointmentDao {

	private static Credential credential;

	public BookAppointmentDaoImpl(Credential credential) {
		BookAppointmentDaoImpl.credential = credential;
	}

	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory;
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(BookAppointmentDTO.class);
		configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/bookappointment");
		configuration.setProperty("hibernate.connection.username", credential.getUSERNAME());
		configuration.setProperty("hibernate.connection.password", credential.getPASSWORD());
		configuration.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");
		configuration.setProperty("hibernate.connection.pool_size", "10");
		ServiceRegistry builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(builder);
		return sessionFactory;
	}

	@Override
	public BookAppointmentDTO insertAppointment(BookAppointmentDTO bookAppointment) {
		SessionFactory sessionactory = BookAppointmentDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.save(bookAppointment);
		session.getTransaction().commit();
		session.close();
		return bookAppointment;
	}

	@Override
	public List<BookAppointmentDTO> getAll() {
		SessionFactory sessionactory = BookAppointmentDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		String hql = "FROM bookappointment";
		Query q = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookAppointmentDTO> appointments = q.list();
		session.getTransaction().commit();
		session.close();
		return appointments;
	}

	@Override
	public BookAppointmentDTO singleAppointment(Long id) {
		SessionFactory sessionactory = BookAppointmentDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		BookAppointmentDTO appointment = (BookAppointmentDTO) session.get(BookAppointmentDTO.class, id);
		session.getTransaction().commit();
		session.close();
		return appointment;
	}

	@Override
	public String deleteAppointment(Long id) {
		SessionFactory sessionactory = BookAppointmentDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		BookAppointmentDTO patientRecord = (BookAppointmentDTO) session.get(BookAppointmentDTO.class, id);
		session.delete(patientRecord);
		session.getTransaction().commit();
		session.close();
		return String.valueOf("Deleted");
	}

	@Override
	public List<String> getTimeSlots(String date, String docType) {
		SessionFactory sessionactory = BookAppointmentDaoImpl.getSessionFactory();
		Session session = sessionactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		String hql = "select u.timeSlot FROM bookappointment u where u.docType='" + docType + "' and u.date='" + date
				+ "'";
		Query q = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> bookedSlots = q.list();
		session.getTransaction().commit();
		session.close();
		return bookedSlots;
	}
}