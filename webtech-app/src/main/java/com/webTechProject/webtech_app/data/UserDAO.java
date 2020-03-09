package com.webTechProject.webtech_app.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webTechProject.webtech_app.model.Users;

@Stateless
@LocalBean
public class UserDAO {

	@PersistenceContext
	private EntityManager em;
	Query query;
	
	public List<Users> getAllUsers(){
		Query query = em.createQuery("Select u from Users u");
		return query.getResultList();
	}

	public Users getUser(int id) {
		return em.find(Users.class, id);
	}
	
	public void save(Users users) {
		em.persist(users);
	}

	public void update(Users users) {
		em.merge(users);
	}

	public void delete(int id) {
		em.remove(getUser(id));
	}

	public List<Users> getuserByUserName(String userName) {
		Query query = em.createQuery("SELECT u FROM Users as u WHERE u.username  LIKE ?1");
		query.setParameter(1, "%" +userName.toUpperCase() +"%");
		return query.getResultList();
	}
	
	public List<Users> loginUser(String userName, String password) {
		//String login = "SELECT * FROM users WHERE users.userName= name and users.password= password";
	
		String login = "SELECT ua FROM users ua WHERE ua.userName=:userName and ua.password=:password";
		query = em.createQuery(login);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.getResultList();
	}
}
