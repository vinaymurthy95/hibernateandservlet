package com.agiliz.hibserv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		String address = request.getParameter("ad");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("reg");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Query query = manager.createQuery("update UserDTO set address='" + address + "' where username='" + username
				+ "' and password='" + password + "'");
		int rows = query.executeUpdate();
		if (rows == 1) {
			pw.print("Updated Successfully");

		} else {
			// TODO Auto-generated catch block
			pw.print("No Records Found , please relogin");

		}

		transaction.commit();
		manager.close();
		factory.close();

	}

}
