package com.softlab.consumer.core.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by LiXiwen on 2019/6/2 20:36.
 **/
@Data
public class Log {
    private String level;
    private String application;
    private String tag;
    private String timestamp;
    private String content;
}
