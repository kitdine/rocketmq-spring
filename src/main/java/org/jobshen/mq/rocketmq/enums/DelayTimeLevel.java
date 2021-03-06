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

package org.jobshen.mq.rocketmq.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ConsumeContext Description:  延迟枚举类
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 14:30
 * @since JDK 1.7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DelayTimeLevel {
  /**
   * 延迟1秒
   */
  ONE_SECOND(1, 1),
  /**
   * 延迟5秒
   */
  FIVE_SECOND(2, 2),
  /**
   * 延迟10秒
   */
  TEN_SECOND(3, 3),
  /**
   * 延迟30秒
   */
  THITY_SECOND(4, 4);

  private int key;
  private int  value;
}
