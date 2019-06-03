package com.softlab.consumer.service.impl;

import com.softlab.consumer.common.ConsumeException;
import com.softlab.consumer.core.mapper.ConsumeMapper;
import com.softlab.consumer.core.model.Log;
import com.softlab.consumer.core.model.LogVo;
import com.softlab.consumer.service.ConsumeService;
import com.softlab.consumer.web.api.ConsumerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by LiXiwen on 2019/5/21 15:34.
 **/
@Service
public class ConsumeServiceImpl implements ConsumeService {

    private static final Logger log= LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumeMapper consumeMapper;

    @Override
    public void addLog(LogVo logVo) throws ConsumeException {

        /*Date time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            log.info("timestamp: "+logVo.getTimestamp());
            //log.info("parse: "+sdf.parse(sdf.format(logVo.getTimestamp())));
            log.info("format: "+sdf.format(new Date(logVo.getTimestamp())));
            logVo.setTimestamp(sdf.format(logVo.getTimestamp()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        log.info("time1: "+logVo.getTimestamp());
        if(0 != consumeMapper.addLog(logVo)){
            log.info("add log success");
        }else{
            throw new ConsumeException("add log error");
        }
    }

    @Override
    public List<Log> selectLog(LogVo logVo) throws ConsumeException {
        List<Log> rtv = consumeMapper.selectLog(logVo);
        if(null != rtv){
            return rtv;
        }else{
            throw new ConsumeException("数据库不存在数据");
        }
    }


}
