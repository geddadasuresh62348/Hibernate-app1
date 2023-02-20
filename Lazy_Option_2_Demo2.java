package hibernate.advanced.Eager_vs_Lazy.Demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hibernate.advanced.Entity.Course;
import hibernate.advanced.Entity.Instructor;
import hibernate.advanced.Entity.InstructorDetails;


public class Lazy_Option_2_Demo2 {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetails.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		// create session
		Session session=factory.getCurrentSession();
		
		try {		
			// Option-2 : Hibernate Query with HQL
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId=7;
			
			Query<Instructor> query=session.createQuery("select i from Instructor i "
											+"JOIN FETCH i.courses "
											+"where i.id=:theInstructorId"
											,Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor instructor=query.getSingleResult();
			
			System.out.println(instructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			System.out.println("\n session is now closed...!");
			
			// get courses from instructor by using HQL query
			System.out.println(" Courses : "+instructor.getCourses());
		}
		finally {
			factory.close();
		}
	}
}
