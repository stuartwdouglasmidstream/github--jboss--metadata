/*
 * JBoss, Home of Professional Open Source
 * Copyright 2007, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.metadata.web.spec;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jboss.metadata.javaee.support.AugmentableMetaData;
import org.jboss.metadata.javaee.support.IdMetaDataImpl;

/**
 * web-app/servlet-mapping metadata
 *
 * @author Scott.Stark@jboss.org
 * @version $Revision: 83549 $
 */
@XmlType(name = "servlet-mappingType", propOrder = { "servletName", "urlPatterns" })
public class ServletMappingMetaData extends IdMetaDataImpl implements AugmentableMetaData<ServletMappingMetaData> {
    private static final long serialVersionUID = 1;
    protected String servletName;
    protected List<String> urlPatterns;

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public List<String> getUrlPatterns() {
        // Resolve special "" mapping as the empty String
        if (urlPatterns != null)
            for (int i = 0; i < urlPatterns.size(); i++) {
                if ("\"\"".equals(urlPatterns.get(i))) {
                    urlPatterns.set(i, "");
                }
            }
        return urlPatterns;
    }

    @XmlElement(name = "url-pattern")
    public void setUrlPatterns(List<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public void augment(ServletMappingMetaData webFragmentMetaData, ServletMappingMetaData webMetaData, boolean resolveConflicts) {
        // Note: as this is purely additive, webMetaData is useless
        // Url pattern
        if (getUrlPatterns() == null) {
            setUrlPatterns(webFragmentMetaData.getUrlPatterns());
        } else if (webFragmentMetaData.getUrlPatterns() != null) {
            List<String> mergedUrlPatterns = new ArrayList<String>();
            for (String urlPattern : getUrlPatterns()) {
                mergedUrlPatterns.add(urlPattern);
            }
            for (String urlPattern : webFragmentMetaData.getUrlPatterns()) {
                boolean found = false;
                for (String check : getUrlPatterns()) {
                    if (check.equals(urlPattern)) {
                        found = true;
                    }
                }
                if (!found)
                    mergedUrlPatterns.add(urlPattern);
            }
            setUrlPatterns(mergedUrlPatterns);
        }
    }

    public String toString() {
        StringBuilder tmp = new StringBuilder("ServletMappingMetaData(id=");
        tmp.append(getId());
        tmp.append(",servletName=");
        tmp.append(servletName);
        tmp.append(",urlPatterns=");
        tmp.append(urlPatterns);
        tmp.append(')');
        return tmp.toString();
    }

}
