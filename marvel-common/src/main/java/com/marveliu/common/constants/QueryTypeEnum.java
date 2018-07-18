package com.marveliu.common.constants;

import io.swagger.annotations.ApiModel;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:52 PM
 **/

@ApiModel(value = "查询条件支持的过滤方式")
public enum QueryTypeEnum {
    like,
    equal,
    ne,
    lt,
    lte,
    gt,
    gte,
    in
}