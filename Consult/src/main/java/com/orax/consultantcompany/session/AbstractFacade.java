/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orax.consultantcompany.session;

import com.orax.consultantcompany.entities.Projects;
import com.orax.consultantcompany.entities.Users;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adnan
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (request.isUserInRole("ED-APP-CLIENT")) {
            Query query1 = getEntityManager().createNamedQuery("Users.findByEmail", entityClass);
            query1.setParameter("email", request.getUserPrincipal().getName());
            Users user = (Users) query1.getSingleResult();
            Projects projects = (Projects) entity;
            projects.setClient(user);
        }
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        System.out.println("URI   " + request.getRequestURI());
        if (request.isUserInRole("ED-APP-CONSULTANT")) {
            if (request.getRequestURI().equals("/ConsultantCompany/faces/consultants/AllProjects.xhtml")) {
                Query query1 = getEntityManager().createNamedQuery("Users.findByEmail", entityClass);
                query1.setParameter("email", request.getUserPrincipal().getName());
                Users user = (Users) query1.getSingleResult();
                Query q = getEntityManager().createNativeQuery("select p.id,p.project_name,p.description,p.client,p.recruiters,p.status "
                        + "from projects p inner join billables b on b.project=p.id where b.consultant=?1 and p.status='Active'",Projects.class);
                q.setParameter(1, user.getId());
                return q.getResultList();

            } else {
                Query query = getEntityManager().createNamedQuery("Billables.findByConsultant", entityClass);
                query.setParameter("consultant_email", request.getUserPrincipal().getName());
                return query.getResultList();
            }
        } else if (request.isUserInRole("ED-APP-CLIENT")) {
            Query query1 = getEntityManager().createNamedQuery("Users.findByEmail", entityClass);
            query1.setParameter("email", request.getUserPrincipal().getName());
            Users user = (Users) query1.getSingleResult();
            Query query = getEntityManager().createNamedQuery("Projects.findByClient", entityClass);
            query.setParameter("client_id", user.getId());
            return query.getResultList();
        } else if (request.isUserInRole("ED-APP-RECRUITER")) {
            Query query1 = getEntityManager().createNamedQuery("Users.findByEmail", entityClass);
            query1.setParameter("email", request.getUserPrincipal().getName());
            Users user = (Users) query1.getSingleResult();
            Query query = getEntityManager().createNamedQuery("Projects.findByRecruiter", entityClass);
            query.setParameter("recruiter_id", user.getId());
            return query.getResultList();
        } else {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }
    }

    public List<T> findBillByConsultant() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        System.out.println(request.getUserPrincipal().getName());
        Query query = getEntityManager().createNamedQuery("Billables.findByConsultant", entityClass);
        query.setParameter("consultant_email", request.getUserPrincipal().getName());
        return query.getResultList();
    }

    public List<T> findProjectByClient() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        System.out.println(request.getUserPrincipal().getName());
        Query query = getEntityManager().createNamedQuery("Projects.findByClient", entityClass);
        query.setParameter("email", request.getUserPrincipal().getName());
        return query.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
