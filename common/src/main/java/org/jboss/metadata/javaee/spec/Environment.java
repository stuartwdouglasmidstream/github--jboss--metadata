/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.metadata.javaee.spec;

/**
 * Environment.
 *
 * @author <a href="adrian@jboss.com">Adrian Brock</a>
 * @version $Revision: 1.1 $
 */
public interface Environment extends RemoteEnvironment {
    /**
     * Get the ejbLocalReferences.
     *
     * @return the ejbLocalReferences.
     */
    EJBLocalReferencesMetaData getEjbLocalReferences();

    /**
     * Get by name
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    EJBLocalReferenceMetaData getEjbLocalReferenceByName(String name);

    /**
     * Get the persistenceContextRefs.
     *
     * @return the persistenceContextRefs.
     */
    PersistenceContextReferencesMetaData getPersistenceContextRefs();

    /**
     * Get by name
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    PersistenceContextReferenceMetaData getPersistenceContextReferenceByName(String name);

    /**
     * Retrieves the context services.
     *
     * @return
     */
    ContextServicesMetaData getContextServices();

    /**
     * Retrieves the context service by name.
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    ContextServiceMetaData getContextServiceByName(String name) throws IllegalArgumentException;

    /**
     * Retrieves the managed executors.
     *
     * @return
     */
    ManagedExecutorsMetaData getManagedExecutors();

    /**
     * Retrieves the managed executor by name.
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    ManagedExecutorMetaData getManagedExecutorByName(String name) throws IllegalArgumentException;

    /**
     * Retrieves the managed scheduled executors.
     *
     * @return
     */
    ManagedScheduledExecutorsMetaData getManagedScheduledExecutors();

    /**
     * Retrieves the managed scheduled executor by name.
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    ManagedScheduledExecutorMetaData getManagedScheduledExecutorByName(String name) throws IllegalArgumentException;

    /**
     * Retrieves the managed thread factories.
     *
     * @return
     */
    ManagedThreadFactoriesMetaData getManagedThreadFactories();

    /**
     * Retrieves the managed thread factory by name.
     *
     * @param name the name
     * @return the result or null if not found
     * @throws IllegalArgumentException for a null name
     */
    ManagedThreadFactoryMetaData getManagedThreadFactoryByName(String name) throws IllegalArgumentException;
}
