/*
 * Copyright (c) 2017 the original author or authors.
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jobshen.mq.rocketmq.message.impl;

import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;

import org.apache.rocketmq.common.message.Message;
import org.jobshen.mq.rocketmq.enums.DelayTimeLevel;
import org.jobshen.mq.rocketmq.execptions.SerializationException;
import org.jobshen.mq.rocketmq.message.DelayMessageInterface;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;

import lombok.extern.log4j.Log4j2;

/**
 * @author <a href="mailto:kitdnie@gmail.com">JobShen<a/>
 * @since JDK1.7 Created on 2017/1/5.
 */
@Log4j2
public class DelaySerializableDefaultMessageMessage<T> implements DelayMessageInterface<T> {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private final String topic;
    private final String tags;
    private final RocketMqSerializer serializer;
    private final Class<T> clazz;

    public DelaySerializableDefaultMessageMessage(String topic, String tags, RocketMqSerializer serializer) {
        this.topic = topic;
        this.tags = tags;
        this.serializer = serializer;
        clazz  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
    }

    @Override
    public Message getInstance(T t, DelayTimeLevel timeLevel) {
        try {
            Message message = new Message(this.topic, this.tags, serializer.serialize(t, clazz));
            message.setDelayTimeLevel(timeLevel.getValue());
            return message;
        } catch (SerializationException e) {
            log.error("Default charset {} not supported, using {} instead", DEFAULT_CHARSET, Charset.defaultCharset().name());
            return new Message(this.topic, this.tags, serializer.serialize(t, clazz));
        }
    }

    @Override
    public Message getInstance(T t, String keys, DelayTimeLevel timeLevel) {
        try {
            Message message = new Message(this.topic, this.tags, keys, serializer.serialize(t, clazz));
            message.setDelayTimeLevel(timeLevel.getValue());
            return message;
        } catch (SerializationException e) {
            log.error("Default charset {} not supported, using {} instead", DEFAULT_CHARSET, Charset.defaultCharset().name());
            return new Message(this.topic, this.tags, serializer.serialize(t, clazz));
        }
    }
}
