/*
 * JBoss, Home of Professional Open Source
 * Copyright 2022, Red Hat, Inc. and/or its affiliates, and individual
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
package org.jboss.metadata.merge.javaee.spec;

import org.jboss.metadata.javaee.spec.ManagedExecutorMetaData;
import org.jboss.metadata.javaee.spec.ManagedExecutorsMetaData;

/**
 *
 * @author emmartins
 *
 */
public class ManagedExecutorsMetaDataMerger {

    /**
     * Merge data sources
     *
     * @param override      the override references
     * @param overriden     the overriden references
     * @param overridenFile the overriden file name
     * @param overrideFile  the override file
     * @return the merged referencees
     */
    public static ManagedExecutorsMetaData merge(ManagedExecutorsMetaData override, ManagedExecutorsMetaData overriden, String overridenFile,
                                                 String overrideFile) {
        if (override == null) {
            return overriden;
        }
        ManagedExecutorsMetaData merged = new ManagedExecutorsMetaData();
        return merge(merged, overriden, override, "managed-executor", overridenFile, overrideFile, false);
    }

    /**
     * From JavaEEMetaDataUtil
     */
    private static ManagedExecutorsMetaData merge(ManagedExecutorsMetaData merged, ManagedExecutorsMetaData overriden,
                                                  ManagedExecutorsMetaData mapped, String context, String overridenFile, String overrideFile, boolean mustOverride) {
        if (merged == null) {
            throw new IllegalArgumentException("Null merged");
        }

        // Nothing to do
        if (overriden == null && mapped == null) {
            return merged;
        }

        // No override
        if (overriden == null || overriden.isEmpty()) {
            // There are no overrides and no overriden
            // Note: it has been really silly to call upon merge, but allas
            if (mapped == null) {
                return merged;
            }

            if (mapped.isEmpty() == false && mustOverride) {
                throw new IllegalStateException(overridenFile + " has no " + context + "s but " + overrideFile + " has "
                        + mapped.keySet());
            }
            if (mapped != merged) {
                merged.addAll(mapped);
            }
            return merged;
        }

        // Wolf: I want to maintain the order of originals (/ override)
        // Process the originals
        for (ManagedExecutorMetaData original : overriden) {
            String key = original.getKey();
            if (mapped != null && mapped.containsKey(key)) {
                ManagedExecutorMetaData override = mapped.get(key);
                ManagedExecutorMetaData tnew = ManagedExecutorMetaDataMerger.merge(override, original);
                merged.add(tnew);
            } else {
                merged.add(original);
            }
        }

        // Process the remaining overrides
        if (mapped != null) {
            for (ManagedExecutorMetaData override : mapped) {
                String key = override.getKey();
                if (merged.containsKey(key)) {
                    continue;
                }
                if (mustOverride) {
                    throw new IllegalStateException(key + " in " + overrideFile + ", but not in " + overridenFile);
                }
                merged.add(override);
            }
        }

        return merged;
    }

    public static void augment(ManagedExecutorsMetaData dest, ManagedExecutorsMetaData augment,
                               ManagedExecutorsMetaData main, boolean resolveConflicts) {
        for (ManagedExecutorMetaData metaData : augment) {
            if (dest.containsKey(metaData.getKey())) {
                if (!resolveConflicts && (main == null || !main.containsKey(metaData.getKey()))) {
                    throw new IllegalStateException("Unresolved conflict on metadata named: " + metaData.getKey());
                }
            } else {
                dest.add(metaData);
            }
        }
    }
}
