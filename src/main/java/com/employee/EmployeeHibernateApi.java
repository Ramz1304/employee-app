package com.employee;


import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;



public class EmployeeHibernateApi  {

	private static Logger Log = Logger.getLogger(EmployeeHibernateApi.class);
	

	
private HibernateUtil hbu;



	

	public EmployeeHibernateApi()  {
		hbu = new HibernateUtil();
	}

	public void Insert(employeePojo p) throws SQLException {
		Log.info("inserting employee");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		s.save(p);
		
		hbu.commitTransaction();
		hbu.closeSession();
		
		
		
	}
	
	

	public employeePojo Select(int id) throws SQLException {
		Log.info("selecting employee");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		employeePojo p = s.find(employeePojo.class,id);
		
		hbu.commitTransaction();
		hbu.closeSession();
		return p;
		

	}
	
	public List<employeePojo> SelectAll() throws SQLException {
		Log.info("selecting all employee");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		Query q = s.createQuery("select o from employeePojo o");
		List<employeePojo> list = q.getResultList();
		hbu.commitTransaction();
		hbu.closeSession();
		return list;
		

	}

	public void Delete(int id) throws SQLException {
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		employeePojo p = s.find(employeePojo.class,id);
		s.delete(p);
		
		hbu.commitTransaction();
		hbu.closeSession();
		
		
	}
	
	public void DeleteAll() throws SQLException {
		
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		Query q = s.createQuery("select o from employeePojo o");
		List<employeePojo> list = q.getResultList();
		for(employeePojo p: list) {
			s.delete(p);
		}
		
		hbu.commitTransaction();
		hbu.closeSession();
		
		
	}
	
	public void printAll() throws SQLException {
		List<employeePojo> list = SelectAll();
		for(employeePojo p: list) {
			Log.warn("employee id: " + p.getId() + ", name: " + p.getName() + ", age: " +p.getAge() );
		}
		}

	public void Update(int id, employeePojo p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
		
	}


