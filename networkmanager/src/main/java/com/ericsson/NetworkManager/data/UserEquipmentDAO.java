package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.UserEquipment;

@Stateless
@LocalBean
public class UserEquipmentDAO {

	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public void create(final UserEquipment userEquipment) {
		entityManager.persist(userEquipment);
	}
	
	public List<UserEquipment> getAllUserEquipment() {
		query = entityManager.createQuery("SELECT b FROM UserEquipment b");
		return query.getResultList();
	}
}