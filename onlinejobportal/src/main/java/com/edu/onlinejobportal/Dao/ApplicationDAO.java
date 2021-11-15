package com.edu.onlinejobportal.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Application;
import com.edu.onlinejobportal.Pojo.Company;
import com.edu.onlinejobportal.Pojo.Jobs;

public class ApplicationDAO extends DAO{

	public Application create(Application application) {
		
		try {
			begin();
			getSession().save(application);
			commit();
		} catch(Exception e) {
			rollback();
		} finally {
			close();
		}
		return application;				
	}
	
	public List<Application> list(long id) {
		List<Application> list = new ArrayList<Application>();
		try {
			begin();
			Query q = getSession().createQuery("FROM Application WHERE applicant_id=:applicant_id");
			q.setLong("applicant_id", id);
			list = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
		return list;
	}
	
	public List<Application> list_jobId(long id) {
		List<Application> list = new ArrayList<Application>();
		try {
			begin();
			Query q = getSession().createQuery("FROM Application WHERE jobs_id=:jobs_id");
			q.setLong("jobs_id", id);
			list = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
		return list;
	}
	
	public Application get(long applicationId) {
		Application application = new Application();
		
		try {
			begin();
			Query q = getSession().createQuery("FROM Application WHERE id=:id");
			q.setLong("id", applicationId);
			application = (Application)q.uniqueResult();
			commit();
		}catch (Exception e) {
			// TODO: handle exception
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}
		
		return application;
	}
	
	public void delete(Application application) {
		try {
			begin();
			getSession().delete(application);
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
	}
	
}