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

package org.jobshen.mq.rocketmq.execptions;

import lombok.NoArgsConstructor;

/**
 * RocketMqClientException Description: 通用异常类
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 15:26
 * @since JDK 1.7
 */
@NoArgsConstructor
public class RocketMqClientException extends RuntimeException {
    private static final long serialVersionUID = 5755356574640041094L;

    public RocketMqClientException(String message) {
        super(message);
    }

    public RocketMqClientException(Throwable cause) {
        super(cause);
    }

    public RocketMqClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
