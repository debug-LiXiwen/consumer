package com.softlab.consumer.core.mapper;

import com.softlab.consumer.core.mapper.provider.ConsumeProvider;
import com.softlab.consumer.core.model.Log;
import com.softlab.consumer.core.model.LogVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by LiXiwen on 2019/6/2 19:58.
 **/
@Repository
@Mapper
public interface ConsumeMapper {

    @Insert("INSERT INTO log(log_level,log_application,log_tag,log_timestamp,log_content) VALUES(#{level},#{application},#{tag},#{timestamp},#{content})")
    int addLog(LogVo logVo);

    @SelectProvider(type = ConsumeProvider.class , method = "selectLog")
    List<Log> selectLog(LogVo logVo);
}
