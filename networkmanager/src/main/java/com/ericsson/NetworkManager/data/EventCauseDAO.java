package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.EventCause;

@Stateless
@LocalBean
public class EventCauseDAO {

	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public void create(final EventCause eventCause) {
		entityManager.persist(eventCause);
	}

	public List<EventCause> getAllEventCause() {
		query = entityManager.createQuery("SELECT b FROM EventCause b");
		return query.getResultList();
	}
}