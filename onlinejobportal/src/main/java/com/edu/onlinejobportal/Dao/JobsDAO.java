package com.edu.onlinejobportal.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Company;
import com.edu.onlinejobportal.Pojo.Jobs;

public class JobsDAO extends DAO{

	public Jobs create(Jobs jobs) {
		
		try {
			begin();
			getSession().save(jobs);
			commit();
		} catch(Exception e) {
			rollback();
		} finally {
			close();
		}
		return jobs;				
	}
	
	public List<Jobs> list(long id) {
		List<Jobs> list = new ArrayList<Jobs>();
		try {
			begin();
			Query q = getSession().createQuery("FROM Jobs WHERE company_id=:company_id");
			q.setLong("company_id", id);
			list = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
		return list;
	}
	
	public List<Jobs> totalList() {
		List<Jobs> list = new ArrayList<Jobs>();
		try {
			begin();
			Query q = getSession().createQuery("FROM Jobs");
			list = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
		return list;
	}
	
	public Jobs get(long jobId) {
		Jobs job = new Jobs();
		
		try {
			begin();
			Query q = getSession().createQuery("FROM Jobs WHERE id=:id");
			q.setLong("id", jobId);
			job = (Jobs)q.uniqueResult();
			commit();
		}catch (Exception e) {
			// TODO: handle exception
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}
		
		return job;
	}
	
	public void delete(Jobs jobs) {
		try {
			begin();
			getSession().delete(jobs);
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
	}
	
}