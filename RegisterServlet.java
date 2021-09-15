package com.agiliz.hibserv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String fullname = request.getParameter("fn");
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		String phone = request.getParameter("ph");
		long phonenumber = Long.parseLong(phone);
		String address = request.getParameter("ad");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("reg");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		UserDTO us = new UserDTO();
		us.setFullname(fullname);
		us.setUsername(username);
		us.setPassword(password);
		us.setPhone(phonenumber);
		us.setAddress(address);

		manager.persist(us);
		transaction.commit();
		manager.close();
		factory.close();
		pw.print("Registration Done");
	}

}
