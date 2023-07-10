/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orax.consultantcompany.session;

import com.orax.consultantcompany.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author adnan
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public List<Users> getAllRecruiters() {
        Query query = getEntityManager().createNamedQuery("Users.findAllRecruiters", Users.class);
        return query.getResultList();
    }

    public List<Users> getAllConsultants() {
        Query query = getEntityManager().createNamedQuery("Users.findAllConsultants", Users.class);
        return query.getResultList();
    }
}
