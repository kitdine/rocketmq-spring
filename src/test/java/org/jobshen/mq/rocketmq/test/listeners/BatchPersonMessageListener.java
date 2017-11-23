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

package org.jobshen.mq.rocketmq.test.listeners;

import java.util.List;

import org.apache.rocketmq.common.message.MessageExt;
import org.jobshen.mq.rocketmq.bean.ConsumeContext;
import org.jobshen.mq.rocketmq.messagelisteners.AbstractBatchMessageListener;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;
import org.jobshen.mq.rocketmq.test.po.Person;

import lombok.extern.log4j.Log4j2;

/**
 * BatchPersonMessageListener Description:
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 23:33
 * @since JDK 1.8
 */
@Log4j2
public class BatchPersonMessageListener extends AbstractBatchMessageListener<Person> {

    public BatchPersonMessageListener(RocketMqSerializer serializer) {
        super(serializer);
    }

    @Override
    public boolean handleMessage(List<Person> list, List<MessageExt> messages, ConsumeContext context) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            log.info("person : {}, ext : {}", list.get(i).toString(), messages.get(i));
        }
        return true;
    }
}
