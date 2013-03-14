/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.greeter.domain;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateful
public class EJBUserDao implements UserDao {

    @Inject
    private EntityManager entityManager;

    public User getForUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery("findUserByUserName");
            query.setParameter("userName", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        try {
            Query query = entityManager.createNamedQuery("findUserByUserName");
            query.setParameter("userName", user.getUsername());
            User originalUser = (User) query.getSingleResult();
            if(originalUser!=null){
                originalUser.setLastName(user.getLastName());
                originalUser.setFirstName(user.getFirstName());
                entityManager.merge(originalUser);
            }
        } catch (NoResultException e) {
        }

    }

}
