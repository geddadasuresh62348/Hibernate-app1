package hibernate.advanced.Eager_vs_Lazy.Demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.advanced.Entity.Course;
import hibernate.advanced.Entity.Instructor;
import hibernate.advanced.Entity.InstructorDetails;


public class Lazy_Loading_Testing_Demo {

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
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId=7;
			Instructor instructor=session.get(Instructor.class, theId);
			
			System.out.println(" Instructor : "+instructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			System.out.println("\n session is now closed...!");
			
			// since our courses are lazy loaded ,it should raise error
			System.out.println(" Courses : "+instructor.getCourses());
		}
		finally {
			factory.close();
		}
	}
}
