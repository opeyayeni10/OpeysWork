package com.ericsson.NetworkManager.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.NetworkManager.model.MccMnc;

@Stateless
@LocalBean
public class MccMncDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	Query query;

	public void create(final MccMnc mccMnc) {
		entityManager.persist(mccMnc);
	}
	
	public List<MccMnc> getAllMccMnc() {
		query = entityManager.createQuery("SELECT b FROM MccMnc b");
		return query.getResultList();
	}
}
