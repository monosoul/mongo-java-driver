/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mongodb.client.model.search;

import com.mongodb.annotations.Beta;
import com.mongodb.annotations.Sealed;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

/**
 * Represents optional fields of the {@code $vectorSearch} pipeline stage of an aggregation pipeline.
 *
 * @see Aggregates#vectorSearch(FieldSearchPath, Iterable, String, long, long, VectorSearchOptions)
 * @mongodb.atlas.manual atlas-vector-search/vector-search-stage/ $vectorSearch
 * @mongodb.server.release 6.0.10
 * @since 4.11
 */
@Sealed
@Beta(Beta.Reason.SERVER)
public interface VectorSearchOptions extends Bson {
    /**
     * Creates a new {@link VectorSearchOptions} with the filter specified.
     *
     * @param filter A filter that is applied before applying the
     * {@link Aggregates#vectorSearch(FieldSearchPath, Iterable, String, long, long, VectorSearchOptions) queryVector}.
     * One may use {@link Filters} to create this filter, though not all filters may be supported.
     * See the MongoDB documentation for the list of supported filters.
     * <p>
     * Note that for now one has to use {@link Filters#eqFull(String, Object)} instead of {@link Filters#eq(String, Object)}.</p>
     * @return A new {@link VectorSearchOptions}.
     */
    VectorSearchOptions filter(Bson filter);

    /**
     * Creates a new {@link VectorSearchOptions} with the specified option in situations when there is no builder method
     * that better satisfies your needs.
     * This method cannot be used to validate the syntax.
     * <p>
     * <i>Example</i><br>
     * The following code creates two functionally equivalent {@link VectorSearchOptions} objects,
     * though they may not be {@linkplain Object#equals(Object) equal}.
     * <pre>{@code
     *  VectorSearchOptions options1 = VectorSearchOptions.vectorSearchOptions()
     *      .filter(Filters.lt("fieldName", 1));
     *  VectorSearchOptions options2 = VectorSearchOptions.vectorSearchOptions()
     *      .option("filter", Filters.lt("fieldName", 1));
     * }</pre>
     *
     * @param name The option name.
     * @param value The option value.
     * @return A new {@link VectorSearchOptions}.
     */
    VectorSearchOptions option(String name, Object value);

    /**
     * Returns {@link VectorSearchOptions} that represents server defaults.
     *
     * @return {@link VectorSearchOptions} that represents server defaults.
     */
    static VectorSearchOptions vectorSearchOptions() {
        return VectorSearchConstructibleBson.EMPTY_IMMUTABLE;
    }
}
