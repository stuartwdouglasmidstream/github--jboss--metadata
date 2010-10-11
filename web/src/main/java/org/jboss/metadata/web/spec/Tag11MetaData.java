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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * taglib/tag metadata
 *
 * @author Remy Maucherat
 * @version $Revision: 75201 $
 */
@XmlType(name = "tagType", propOrder = { "name", "tagclass", "teiclass", "bodycontent", "info", "attributes" })
public class Tag11MetaData extends TagMetaData {
    private static final long serialVersionUID = 1;

    private String info;

    @XmlElement(name = "tagclass")
    public void setTagclass(String tagClass) {
        super.setTagClass(tagClass);
    }

    @XmlElement(name = "teiclass")
    public void setTeiclass(String teiClass) {
        super.setTeiClass(teiClass);
    }

    @XmlElement(name = "bodycontent")
    public void setBodycontent(BodyContentType bodyContent) {
        super.setBodyContent(bodyContent);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
