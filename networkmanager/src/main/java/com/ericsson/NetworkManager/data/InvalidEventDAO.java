package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.InvalidEvent;

@Stateless
@LocalBean
public class InvalidEventDAO {
	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public void save(final InvalidEvent invalidEvent) {
		entityManager.persist(invalidEvent);
	}

	public List<InvalidEvent> getAllInvalidEvents() {
		query = entityManager.createQuery("SELECT i FROM InvalidEvent i");
		return query.getResultList();
	}
}
