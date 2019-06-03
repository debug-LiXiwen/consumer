package com.softlab.consumer.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlab.consumer.common.ConsumeException;
import com.softlab.consumer.common.RestData;
import com.softlab.consumer.common.util.JsonUtil;
import com.softlab.consumer.core.model.Log;
import com.softlab.consumer.core.model.LogVo;
import com.softlab.consumer.service.ConsumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/4/2 16:53.
 **/
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@Component
public class ConsumerController {

    private static final Logger log= LoggerFactory.getLogger(ConsumerController.class);

    private final ObjectMapper objectMapper;
    private final ConsumeService consumeService;

    @Autowired
    public ConsumerController(ConsumeService consumeService,ObjectMapper objectMapper){
        this.consumeService = consumeService;
        this.objectMapper= objectMapper;
    }

    /**
     * 一个队列  一个消费者
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    @RabbitHandler
    public void consumeUserLogQueue(@Payload byte[] message){

        try{
            LogVo logVo = objectMapper.readValue(message, LogVo.class);
            log.info("接收到对象消息 : logVo = "+JsonUtil.getJsonString(logVo));
            consumeService.addLog(logVo);
        }catch (Exception e){
            log.error("监听消费消息 发生异常： ",e.fillInStackTrace());
        }
    }

    /**
     * 两个队列，一个队列一个消费者
     * @param
     */
    /*@RabbitListener(queues = "${log.users.queue.name1}",containerFactory = "singleListenerContainer")
    @RabbitHandler
    public void consumeMessage(@Payload byte[] message){
        try{
            //TODO：接收对象
            LogVo logVo = objectMapper.readValue(message, LogVo.class);
            log.info("接收到对象消息 : logVo = "+JsonUtil.getJsonString(logVo));
        }catch (Exception e){
            log.error("监听消费消息 发生异常： ",e.fillInStackTrace());
        }
    }*/

    @RequestMapping(value = "/selectLog" , method = RequestMethod.POST)
    public RestData selectLog(@RequestBody LogVo logVo){
        log.info("POST selectLog : "+JsonUtil.getJsonString(logVo));
        try{
            List<Log> data = consumeService.selectLog(logVo);
            return new RestData(data);
        } catch(ConsumeException e){
            return new RestData(1,e.getMessage());
        }
    }

}
