package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.FailureClass;

@Stateless
@LocalBean
public class FailureClassDAO {

	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public void create(final FailureClass failureClass) {
		entityManager.persist(failureClass);
	}
	
	public List<FailureClass> getAllFailureClass() {
		query = entityManager.createQuery("SELECT b FROM FailureClass b");
		return query.getResultList();
	}
}
