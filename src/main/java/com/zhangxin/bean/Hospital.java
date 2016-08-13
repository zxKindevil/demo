package com.zhangxin.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.hat.internal.server.HistogramQuery;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class Hospital {
    public Inner inner;
    public String hosCode;

    public static class Inner {
        public String hosCode;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Hospital hospital = new Hospital();
        hospital.inner = new Inner();
        hospital.inner.hosCode = "H32434324";
        hospital.hosCode="H111111";
        System.out.println(new ObjectMapper().writeValueAsString(hospital));
    }
}
