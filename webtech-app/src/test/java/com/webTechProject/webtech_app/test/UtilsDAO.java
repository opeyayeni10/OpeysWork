package com.webTechProject.webtech_app.test;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager em;
    
	public void deleteTable(){
		em.createQuery("DELETE FROM Book").executeUpdate();
		em.createNativeQuery("ALTER TABLE wine AUTO_INCREMENT=1")
		.executeUpdate();
		
	}

}
