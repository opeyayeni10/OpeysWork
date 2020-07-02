package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.BaseData;

@Stateless
@LocalBean
public class BaseDataDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	Query query;

	public void create(final BaseData baseData) {
		entityManager.persist(baseData);
	}
	/*
	public List<BaseData> findByIMSI(final String imsi) {
		query = entityManager.createQuery("SELECT b.eventId, b.causeCode FROM BaseData b WHERE imsi= " + imsi);
		return query.getResultList();
	}
	 */
	public List<BaseData> getAllBaseData() {
		query = entityManager.createQuery("SELECT b FROM BaseData b");
		return query.getResultList();
	}
	
	public void deleteNulls() {
			query = entityManager.createQuery("delete b from BaseData b where event_id  is null or cause_code is null" );
			query.executeUpdate();
	}

}