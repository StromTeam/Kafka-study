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

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BaoFeng
 * @version 1.0
 * @date 2019/12/17 18:06
 */
@RestController
public class ConsumerController {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMsg(String messge){
        System.out.println("Kafka 消费者接收到消息"+messge);
    }
}
