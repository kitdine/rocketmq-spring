package org.jobshen.mq.rocketmq.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 延迟枚举类
 * @author <a href="mailto:kitdnie@gmail.com">JobShen<a/>
 * @since JDK1.7 Created on 2017/1/2.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DelayTimeLevel {
  ONE_SECOND(1, 1), FIVE_SECOND(2, 2), TEN_SECOND(3, 3), THITY_SECOND(4, 4);

  private int key;

  private int  value;
}
