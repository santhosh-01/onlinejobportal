	package com.edu.onlinejobportal.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.JobDescription;
import com.edu.onlinejobportal.Pojo.Jobs;

public class ApplicantDAO extends DAO{

	public Applicant create(Applicant applicant) {
		
		try {
			begin();
			getSession().save(applicant);
			commit();
		} catch(Exception e) {
			rollback();
		} finally {
			close();
		}
		return applicant;				
	}
	
	public List<String> emails(){
		
		try {
			begin();
			Query query = getSession().createQuery("select email from Applicant");
			List<String> used_emails = query.list();
			commit();
			return used_emails;
		}catch(Exception e) {
			rollback();
		}finally {
			close();
		}
		
		return null;
		
	}
	
	public Applicant update(Applicant applicant) {
		
		Query query = getSession().createQuery("update Applicant set resumefile=:resume where id=:id");
		query.setString("resume", applicant.getResumefile());
		query.setLong("id", applicant.getId());
		
		
		
		try {			
			begin();
			query.executeUpdate();
			commit();
			System.out.println("update committed");
		} catch(Exception e) {
			rollback();
			e.printStackTrace();
			System.out.println("rollbacked");
		}finally {
			close();
		}
		return applicant;
	}
	
	public Applicant authenticate(Applicant applicant) {
		Applicant localApplicant = null;
		try {
			begin();
			System.out.println(applicant.getEmail());
			System.out.println(applicant.getPassword());
			Query q= getSession().createQuery("FROM Applicant WHERE email= :email AND password= :password");
			q.setString("email", applicant.getEmail());
            q.setString("password", applicant.getPassword());
            
            localApplicant =(Applicant) q.uniqueResult();
            
		}catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
		return localApplicant;
	}
		
}