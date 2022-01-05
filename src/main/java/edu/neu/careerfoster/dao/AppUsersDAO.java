package edu.neu.careerfoster.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import  edu.neu.careerfoster.pojo.AppUsers;

public class AppUsersDAO extends DAO {
	// method for Login query
	public AppUsers get(String username, String password) throws Exception{
		try {
			begin();
			Query query = getSession().createQuery("from AppUsers where username = :username and password = :password");
			query.setString("username", username);
			query.setString("password", password);
			AppUsers user = (AppUsers) query.uniqueResult();
			close();
			
			if(user == null) {
				return null;
			}else {
				return user;
			}		
		}catch(HibernateException e){
			rollback();
			throw new Exception("Invalid username or password");
		}
		
	}
	
	// method to register user
	public AppUsers registerUser(AppUsers user) {
		try {
			begin();
			getSession().save(user);
			commit();
			close();
			return user;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			return null;
		}
		
	}
	
	//method for validation for registration
	public boolean registerCriteria(String email, String username) {
		try {
			boolean result = true;
			begin();
			Criteria criteria = getSession().createCriteria(AppUsers.class);
			Criteria criteria1 = getSession().createCriteria(AppUsers.class);
			criteria.add(Restrictions.eq("email", email));
			List<AppUsers> crit1 = criteria.list();
			criteria1.add(Restrictions.eq("username", username));
			List<AppUsers> crit2 = criteria1.list();
			commit();
			close();
			if((crit1.size() >= 1) || (crit2.size() >= 1)) {
				result = true;
			}else {
				result = false;
			}
			return result;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return true;
	}
}

