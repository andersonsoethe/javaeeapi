package com.gutosoethe.connection;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {

    @PersistenceUnit(unitName = "javaeeapiPU")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create(){
        return this.entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes @Default EntityManager entityManager){
        if (entityManager.isOpen()){
            entityManager.close();
        }
    }

}
