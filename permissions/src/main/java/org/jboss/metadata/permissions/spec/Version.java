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
package org.jboss.metadata.permissions.spec;

/**
 *
 * @author Eduardo Martins
 *
 */
public enum Version {

    // always first
    UNKNOWN(null),

    PERMISSIONS_7_0("7"),

    PERMISSIONS_9_0("9"),

    PERMISSIONS_10_0("10");

    private final String name;

    Version(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Version fromString(String s) {
        if(s.equals(PERMISSIONS_7_0.name)) {
            return PERMISSIONS_7_0;
        } else if (s.equals(PERMISSIONS_9_0.name)) {
            return PERMISSIONS_9_0;
        } else if (s.equals(PERMISSIONS_10_0.name)) {
            return PERMISSIONS_10_0;
        }
        return UNKNOWN;
    }

}
