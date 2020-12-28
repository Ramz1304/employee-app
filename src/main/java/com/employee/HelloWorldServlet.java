package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloWorld", description = "sample servlet", urlPatterns = { "/api"})

public class HelloWorldServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 4112660863637769085L;
	
	private static EmployeeHibernateApi api;
	
	static {
		
		HibernateUtil.configure();
		api = new EmployeeHibernateApi();
	}
	
	
	@Override
	//Retrieving
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		
		String actionParam = req.getParameter("action");
		String idParam = req.getParameter("id");
		
		if(idParam == null)
		{
			try {
				List<employeePojo> list = api.SelectAll();
				for(employeePojo p:list) {
					print (out,p);
				}
			} catch (SQLException e) {
				throw new ServletException("error retriving employee list",e);
			}
			return;
		}
		
		if(actionParam!=null && actionParam.contentEquals("delete")) {
			doDelete(req, resp);
            return;		
		}
		int id = Integer.valueOf(idParam);
		
		try {
			employeePojo p = api.Select(id);
			print (out,p);
		} catch (SQLException e) {
			throw new ServletException("error retriving single employee",e);
		}
		
	
	}
     private void print(PrintWriter out, employeePojo p) {
    	
    	 
    	 out.println("<br>");
 		out.println("name: " + p.getName());
 		out.println("<br>");
 		out.println("id: " + p.getId());
 		out.println("<br>");
 		out.println("age: " + p.getAge());
		
		
	}
	//Creating
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String age=req.getParameter("age");
	
	     employeePojo p = new employeePojo();
	     p.setAge(Integer.valueOf(age));
	     p.setId(Integer.valueOf(id));
	     p.setName(name);
		try {
			api.Insert(p);
		} catch (SQLException e) {
			
			throw new ServletException("error saving object",e);
		}
		PrintWriter out = resp.getWriter();
		out.println("Employee created successfully");
	}
	// Updating
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String age=req.getParameter("age");
	
	     employeePojo p = new employeePojo();
	     p.setAge(Integer.valueOf(age));
	     p.setId(Integer.valueOf(id));
	     p.setName(name);
		try {
			api.Update(p.getId(),p);
		} catch (SQLException e) {
			
			throw new ServletException("error updating object",e);
		}
	}
	// Deleting
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<h1>Employee deleted</h1>");
		int id = Integer.valueOf(req.getParameter("id"));
		
		try {
			api.Delete(id);;
		} catch (SQLException e) {
			throw new ServletException("error retriving object",e);
		}
	}
}
