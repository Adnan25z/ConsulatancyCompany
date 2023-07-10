/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orax.consultantcompany.session;

import com.orax.consultantcompany.entities.Billables;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adnan
 */
@Stateless
public class BillablesFacade extends AbstractFacade<Billables> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillablesFacade() {
        super(Billables.class);
    }

    public List<Billables> getAllBillsByConsultant() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        Query query = getEntityManager().createNamedQuery("Billables.findByConsultant", Billables.class);
        query.setParameter("consultant_email", request.getUserPrincipal().getName());
        return query.getResultList();
    }

}
