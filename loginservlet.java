package com.agiliz.hibserv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {// ======read operation===========

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("un");
		String password = request.getParameter("pw");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("reg");
		EntityManager manager = factory.createEntityManager();

		Query query = manager
				.createQuery("from UserDTO where username='" + username + "' and password='" + password + "'");

		try {
			UserDTO dto = (UserDTO) query.getSingleResult();
			pw.print(dto.getFullname() + " " + dto.getAddress() + " " + dto.getPhone() + " " + dto.getId());
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			pw.print("No Records Found , please relogin");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}

		manager.close();
		factory.close();

	}

}
