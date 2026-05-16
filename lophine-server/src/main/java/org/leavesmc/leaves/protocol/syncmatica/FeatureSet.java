/*
 * This file is part of Leaves (https://github.com/LeavesMC/Leaves)
 *
 * Leaves is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Leaves is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Leaves. If not, see <https://www.gnu.org/licenses/>.
 */

package org.leavesmc.leaves.protocol.syncmatica;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class FeatureSet {

    private static final Map<String, FeatureSet> versionFeatures;

    static {
        versionFeatures = new HashMap<>();
        versionFeatures.put("0.1", new FeatureSet(Collections.singletonList(Feature.CORE)));
    }

    private final Collection<Feature> features;

    public FeatureSet(final Collection<Feature> features) {
        this.features = features;
    }

    @Nullable
    public static FeatureSet fromVersionString(@NotNull String version) {
        if (version.matches("^\\d+(\\.\\d+){2,4}$")) {
            final int minSize = version.indexOf(".");
            while (version.length() > minSize) {
                if (versionFeatures.containsKey(version)) {
                    return versionFeatures.get(version);
                }
                final int lastDot = version.lastIndexOf(".");
                version = version.substring(0, lastDot);
            }
        }
        return null;
    }

    @NotNull
    public static FeatureSet fromString(final @NotNull String features) {
        final FeatureSet featureSet = new FeatureSet(new ArrayList<>());
        for (final String feature : features.split("\n")) {
            final Feature f = Feature.fromString(feature);
            if (f != null) {
                featureSet.features.add(f);
            }
        }
        return featureSet;
    }

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        boolean b = false;
        for (final Feature feature : features) {
            output.append(b ? "\n" + feature.toString() : feature.toString());
            b = true;
        }
        return output.toString();
    }

    public boolean hasFeature(final Feature f) {
        return features.contains(f);
    }
}
