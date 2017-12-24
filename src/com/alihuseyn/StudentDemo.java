package com.alihuseyn;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alihuseyn.model.Student;

public class StudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		// create session
		try {
			Session session = factory.getCurrentSession();
			Student student = new Student("Daffy", "Gof", "daffy@gmail.com");
			System.out.println(student);
			session.beginTransaction();
			session.save(student);
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student tempStudent = session.get(Student.class, 1);
			System.out.println(tempStudent);
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Student> students = session.createQuery("from Student").getResultList();
			for(Student temporary : students) {
				System.out.println(temporary);
			}
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
