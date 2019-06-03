package com.softlab.consumer.core.mapper.provider;

import com.softlab.consumer.core.model.LogVo;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by LiXiwen on 2019/6/2 21:14.
 **/
public class ConsumeProvider {

    public String selectLog(LogVo logVo){
        return new SQL() {
            {
                SELECT("log_application AS application ,log_level AS level,log_content AS content,log_timestamp AS timestamp,log_tag AS tag");
                FROM("log");
                if(null != logVo.getApplication() && 0 != logVo.getApplication().length()){
                    WHERE("log_application=#{application}");
                }
                if(null != logVo.getContent() && 0 != logVo.getContent().length()){
                    WHERE("log_content=#{content}");
                }
                if(null != logVo.getLevel() && 0 != logVo.getLevel().length()){
                    WHERE("log_level=#{level}");
                }
                if(null != logVo.getTag() && 0 != logVo.getTag().length()){
                    WHERE("log_tag=#{tag}");
                }
                if(null != logVo.getTimestamp() && 0 != logVo.getTimestamp().length()){
                    WHERE("log_timestamp=#{timestamp}");
                }
                ORDER_BY("log_timestamp DESC");
            }
        }.toString();
    }
}