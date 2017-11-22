/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jobshen.mq.rocketmq.serialize.json;

import org.jobshen.mq.rocketmq.enums.SerializeTypeEnum;
import org.jobshen.mq.rocketmq.execptions.SerializationException;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;

import com.alibaba.fastjson.JSON;

/**
 * FastjsonSerializer Description:
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 15:30
 * @since JDK 1.7
 */
public class FastjsonSerializer implements RocketMqSerializer {

    /**
     * get content type id
     *
     * @return content type id
     */
    @Override
    public byte getContentTypeId() {
        return SerializeTypeEnum.FastJson.getId();
    }

    /**
     * get content type
     *
     * @return content type
     */
    @Override
    public String getContentType() {
        return SerializeTypeEnum.FastJson.getType();
    }

    /**
     * Serialize the given object to binary data.
     *
     * @param t object to serialize
     * @return the equivalent binary data
     */
    @Override
    public <T> byte[] serialize(Object t, Class<T> clazz) throws SerializationException {
        return JSON.toJSONBytes(t);
    }

    /**
     * Deserialize an object from the given binary data.
     *
     * @param bytes object binary representation
     * @return the equivalent object instance
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws SerializationException {
        return JSON.parseObject(bytes,clazz);
    }
}
