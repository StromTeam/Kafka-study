/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2019.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2019/12/17    BaoFeng         Create the class
 * http://www.jimilab.com/
 */


package com.example.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author BaoFeng
 * @version 1.0
 * @date 2019/12/17 18:06
 */
@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate<String,String> template;

    @Value("${kafka.topic}")
    private String topic;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam String message){
        template.send(topic,message);
        return  "send success";
    }
}
