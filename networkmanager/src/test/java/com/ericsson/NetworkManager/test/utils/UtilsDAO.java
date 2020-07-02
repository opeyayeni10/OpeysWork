package com.ericsson.NetworkManager.test.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UtilsDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void deleteAllDataTables() {
		deleteBaseDataTable();
		deleteEventCauseTable();
		deleteFailureClassTable();
		deleteMccMncTable();
		deleteUserEquipmentTable();
	}
	
	public void deleteUserTable() {
		em.createQuery("DELETE FROM User").executeUpdate();
		em.createNativeQuery("ALTER TABLE users AUTO_INCREMENT=1").executeUpdate();
	}

	public void deleteFailureClassTable() {
		em.createQuery("DELETE FROM FailureClass").executeUpdate();
		em.createNativeQuery("ALTER TABLE failure_class AUTO_INCREMENT=1").executeUpdate();
	}

	public void deleteBaseDataTable() {
		em.createQuery("DELETE FROM BaseData").executeUpdate();
		em.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}

	public void deleteUserEquipmentTable() {
		em.createQuery("DELETE FROM UserEquipment").executeUpdate();
	}

	public void deleteMccMncTable() {
		em.createQuery("DELETE FROM MccMnc").executeUpdate();
	}

	public void deleteEventCauseTable() {
		em.createQuery("DELETE FROM EventCause").executeUpdate();
	}
	
	public void deleteInvalidEventTable() {
		em.createQuery("DELETE FROM InvalidEvent").executeUpdate();
		em.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}

}
