package com.springBoot.dao;

import com.springBoot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserByUsername(String email) {
        return em.createQuery("SELECT u FROM User u JOIN FETCH u.roles where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void saveOrUpdate(User user) {
        em.merge(user);
    }


    @Override
    public User getUserById(long id) {
        return em.createQuery("SELECT u FROM User u JOIN FETCH u.roles where u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void delete(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User loadUserByUsername(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u from User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}

