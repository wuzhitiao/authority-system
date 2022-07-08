package com.lens.config.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {
    //路由地址
    private String path;

    //路由对应的组件
    private String component;

    //是否显示
    private boolean alwaysShow;

    //路由名称
    private String name;

    //路由meta信息
    private Mata meta;

    @Data
    @AllArgsConstructor
    public class Mata {

        private String title;//标题

        private String icon;//图标

        private Object[] roles;//角色lieb


    }

    //子路由
    private List<RouterVo> children = new ArrayList<RouterVo>();


}
