package com.edu.onlinejobportal.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Company;


public class CompanyDAO extends DAO{

	public Company create(Company company) {
		
		try {
			begin();
			getSession().save(company);
			commit();
		} catch(Exception e) {
			rollback();
		}finally {
			close();
		}
		return company;				
	}
	
	public List<String> emails(){
		
		try {
			begin();
			Query query = getSession().createQuery("select email from Company");
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
	
	public Company authenticate(Company company) {
		Company localCompany = null;
		try {
			begin();
			System.out.println(company.getEmail());
			System.out.println(company.getPassword());
			Query q= getSession().createQuery("FROM Company WHERE email= :email AND password= :password");
			q.setString("email", company.getEmail());
            q.setString("password", company.getPassword());
            
            localCompany =(Company) q.uniqueResult();
            
		}catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
		return localCompany;
	}
	
	public void update(Company company) {
		try {
			begin();
			getSession().update(company);
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
	}
	
	public void delete(Company company){
		try {
			begin();
			getSession().delete(company);
			commit();
		} catch (HibernateException e) {
			rollback();
		}finally {
			close();
		}
	}
}