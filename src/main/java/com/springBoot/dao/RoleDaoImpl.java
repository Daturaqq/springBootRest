package com.springBoot.dao;

import org.springframework.stereotype.Repository;
import com.springBoot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("SELECT r from Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        em.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }
}

