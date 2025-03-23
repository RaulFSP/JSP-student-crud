package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import jakarta.persistence.EntityManager;
import model.Student;

public class JpaUtil implements Cloneable {

	private static SessionFactory sf;

	private JpaUtil() {
	}


	public static EntityManager getEntityManager()  {
		sf = new Configuration()
				.addAnnotatedClass(Student.class)
				.setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver")
				.setProperty(AvailableSettings.JAKARTA_JDBC_URL,"jdbc:postgresql://localhost:5432/schoolpage")
				.setProperty(AvailableSettings.JAKARTA_JDBC_USER,"postgres")
				.setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD,"123")
				.setProperty(AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION,Action.UPDATE)
				.setProperty(AvailableSettings.SHOW_SQL, true)
				.setProperty(AvailableSettings.FORMAT_SQL,true)
				.setProperty(AvailableSettings.HIGHLIGHT_SQL,true)
				.buildSessionFactory();
		return sf.createEntityManager();
	}

	public static void close() {
		if (sf.isOpen()) {
			sf.close();
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("This class cannot be cloned");
	}
}
