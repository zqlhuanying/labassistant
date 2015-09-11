package com.labassistant.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 各种实体类的基类，因为实体类都是需要序列化的
 * @author zql
 * @date 2015/09/07
 */
public class ToStringBase implements Serializable {

	/**
	 * 序列号ID
	 */
	private static final long serialVersionUID = 1153886676002046909L;

	@Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);

    }
}
