package com.softlab.consumer.service;

import com.softlab.consumer.common.ConsumeException;
import com.softlab.consumer.core.model.Log;
import com.softlab.consumer.core.model.LogVo;

import java.util.List;

/**
 * Created by LiXiwen on 2019/5/21 15:36.
 **/
public interface ConsumeService {
    void addLog(LogVo logVo) throws ConsumeException;
    List<Log> selectLog(LogVo logVo) throws ConsumeException;
}
