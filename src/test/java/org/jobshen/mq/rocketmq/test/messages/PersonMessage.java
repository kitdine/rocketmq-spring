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

package org.jobshen.mq.rocketmq.test.messages;

import org.jobshen.mq.rocketmq.message.impl.SerializableDefaultMessage;
import org.jobshen.mq.rocketmq.test.po.Person;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;

/**
 * PersonMessage Description:
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 22:31
 * @since JDK 1.8
 */
public class PersonMessage extends SerializableDefaultMessage<Person> {

    public PersonMessage(String topic, String tags, RocketMqSerializer serializer) {
        super(topic, tags, serializer);
    }
}
