/*
 *
 *  Copyright 2013 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.zeno.serializer.common;

import com.netflix.zeno.fastblob.record.schema.FastBlobSchema;
import com.netflix.zeno.fastblob.record.schema.FastBlobSchema.FieldType;
import com.netflix.zeno.serializer.NFDeserializationRecord;
import com.netflix.zeno.serializer.NFSerializationRecord;
import com.netflix.zeno.serializer.NFTypeSerializer;

import java.util.Collection;
import java.util.Collections;

/**
*
* A default NFTypeSerializer implementation for String objects.
*
*/
public class StringSerializer extends NFTypeSerializer<String> {

    public static final String NAME = "Strings";

    public StringSerializer() {
        super(NAME);
    }

    public StringSerializer(String typeName) {
        super(typeName);
    }

    @Override
    public void doSerialize(String value, NFSerializationRecord rec) {
        serializePrimitive( rec, "value", value);
    }

    @Override
    protected String doDeserialize(NFDeserializationRecord rec) {
        return deserializePrimitiveString(rec, "value");
    }

    @Override
    protected FastBlobSchema createSchema() {
        return schema(
            field("value", FieldType.STRING)
        );
    }

    @Override
    public Collection<NFTypeSerializer<?>> requiredSubSerializers() {
        return Collections.emptyList();
    }

}
