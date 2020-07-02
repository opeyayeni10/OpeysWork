package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.User;

@Stateless
@LocalBean

public class UserDAO {
	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public List<User> getAllUsers() {
		query = entityManager.createQuery("Select u from User u");
		return query.getResultList();
	}

	public void save(final User user) {
		System.out.println("===================" + user.getName());
		entityManager.persist(user);
	}

	public User findUserByName(final String name) {
		User user = entityManager.createQuery("SELECT u from User u WHERE u.name = :userName", User.class)
				.setParameter("userName", name).getSingleResult();
		return entityManager.find(User.class, name);
	}

/*	public String checkUserAvailability(final User userName) {
		final String name = userName.getName();
		final User user = entityManager.find(User.class, name);
		if (user == null) {
			return "available";
		} else {

			return "unavailable";
		}
	}*/


	public List<User> loginUser(final String name, final String password) {
		final String select = "SELECT ua FROM User ua WHERE ua.name=:user_name and ua.password=:user_password";
		query = entityManager.createQuery(select);
		query.setParameter("user_name", name);
		query.setParameter("user_password", password);
		return query.getResultList();
	}

}
