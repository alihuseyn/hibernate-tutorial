package com.alihuseyn;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alihuseyn.model.Instructor;
import com.alihuseyn.model.InstructorDetail;
import com.alihuseyn.model.Student;

public class StudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		// create session
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			Instructor theInstructor = new Instructor("Alihuseyn", "Gulmammadov", "alihuseyn13@gmail.com");
			InstructorDetail theDetail = new InstructorDetail("http://www.youtube.com", "programming");
			
			theInstructor.setInstructorDetail(theDetail);
			
			session.save(theInstructor);
			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
