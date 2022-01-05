package edu.neu.careerfoster.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import edu.neu.careerfoster.pojo.Role;

public class RoleDAO extends DAO {
//	method to set the role
	public Role get(String role_name) throws Exception {
		try {
			begin();
			Query query = getSession().createQuery("from Role where role_name = :role_name");
			query.setString("role_name", role_name);
			Role role = (Role) query.uniqueResult();
			close();
			return role;
		}catch(HibernateException e){
			rollback();
			throw new Exception("Role not found");
		}
	}
}
