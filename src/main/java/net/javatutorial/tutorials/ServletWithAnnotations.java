package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@WebServlet(name = "simpleServlet", urlPatterns = { "/hello" }, loadOnStartup = 1)
public class ServletWithAnnotations extends HttpServlet {

	private static final long serialVersionUID = -3462096228274971485L;

	/* Testing the persistence libraries
	From pom.xml:
	<dependency>
        <groupId>org.eclipse.persistence</groupId>
        <artifactId>javax.persistence</artifactId>
        <version>2.0.0</version>
    </dependency>
	*/
	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "JPADemo";  
 
	static {
    	emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {

		EntityManager entityMgr = getEntityManager();
		entityMgr.getTransaction().begin();
		 
		Farmer farmObj = new Farmer();
		farmObj.setId(101);
		farmObj.setName("Harry Potter");
		farmObj.setVillage("Scottish Highlands");
		entityMgr.persist(farmObj);
		 
		entityMgr.getTransaction().commit();
		 
		entityMgr.clear();
		// System.out.println("Record Successfully Inserted In The Database");
				
		response.getWriter().println("Record Successfully Inserted In The Database");
	}

}
