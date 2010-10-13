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

import org.jboss.metadata.javaee.spec.ParamValueMetaData;
import org.jboss.metadata.javaee.support.NamedMetaDataWithDescriptionGroup;

/**
 * @author Scott.Stark@jboss.org
 * @version $Revision: 83549 $
 */
public class FilterMetaData extends NamedMetaDataWithDescriptionGroup {
    private static final long serialVersionUID = 1;

    /** The filter class */
    private String filterClass;
    /** The filter init-params */
    private List<ParamValueMetaData> initParam;
    private boolean asyncSupported = false;
    private boolean asyncSupportedSet = false;

    public String getFilterName() {
        return getName();
    }

    public void setFilterName(String name) {
        super.setName(name);
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public List<ParamValueMetaData> getInitParam() {
        return initParam;
    }

    public void setInitParam(List<ParamValueMetaData> initParam) {
        this.initParam = initParam;
    }

    public boolean isAsyncSupported() {
        return asyncSupported;
    }

    public void setAsyncSupported(boolean asyncSupported) {
        this.asyncSupported = asyncSupported;
        asyncSupportedSet = true;
    }

    public void augment(FilterMetaData webFragmentMetaData, FilterMetaData webMetaData, boolean resolveConflicts) {
        // Filter class
        if (getFilterClass() == null) {
            setFilterClass(webFragmentMetaData.getFilterClass());
        } else if (webFragmentMetaData.getFilterClass() != null) {
            if (!resolveConflicts && !getFilterClass().equals(webFragmentMetaData.getFilterClass())
                    && (webMetaData == null || webMetaData.getFilterClass() == null)) {
                throw new IllegalStateException("Unresolved conflict on filter class for filter: " + getName());
            }
        }
        // Init params
        if (getInitParam() == null) {
            setInitParam(webFragmentMetaData.getInitParam());
        } else if (webFragmentMetaData.getInitParam() != null) {
            List<ParamValueMetaData> mergedInitParams = new ArrayList<ParamValueMetaData>();
            for (ParamValueMetaData initParam : getInitParam()) {
                mergedInitParams.add(initParam);
            }
            for (ParamValueMetaData initParam : webFragmentMetaData.getInitParam()) {
                boolean found = false;
                for (ParamValueMetaData check : getInitParam()) {
                    if (check.getParamName().equals(initParam.getParamName())) {
                        found = true;
                        // Check for a conflict
                        if (!resolveConflicts && !check.getParamValue().equals(initParam.getParamValue())) {
                            // If the parameter name does not exist in the main
                            // web, it's an error
                            boolean found2 = false;
                            if (webMetaData.getInitParam() != null) {
                                for (ParamValueMetaData check1 : webMetaData.getInitParam()) {
                                    if (check1.getParamName().equals(check.getParamName())) {
                                        found2 = true;
                                        break;
                                    }
                                }
                            }
                            if (!found2)
                                throw new IllegalStateException("Unresolved conflict on init parameter: "
                                        + check.getParamName());
                        }
                    }
                }
                if (!found)
                    mergedInitParams.add(initParam);
            }
            setInitParam(mergedInitParams);
        }
        // Async supported
        if (!asyncSupportedSet) {
            if (webFragmentMetaData.asyncSupportedSet) {
                setAsyncSupported(webFragmentMetaData.isAsyncSupported());
            }
        } else {
            if (!resolveConflicts && webFragmentMetaData.asyncSupportedSet
                    && (isAsyncSupported() != webFragmentMetaData.isAsyncSupported())
                    && (webMetaData == null || !webMetaData.asyncSupportedSet)) {
                throw new IllegalStateException("Unresolved conflict on async supported");
            }
        }
    }

}
