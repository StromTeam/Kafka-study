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


package com.example.kafkademo.controller.test;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * KAFKA 消费者
 * @author Wakaka
 * @version 1.0
 * @date 2019/12/17 14:02
 */
public class Consumer extends ShutdownableThread {
    private KafkaConsumer<Integer,String> kafkaConsumer;

    public  Consumer(){
        super("KafkaConsumerTest",false);
        Properties properties = new Properties();
        String brokers = "192.168.220.132:9092,192.168.220.133:9092,192.168.220.134:9092";
        properties.put("bootstrap.servers",brokers);
        properties.put("group.id","cityGrop");
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("heartbeat.interval.ms","1000");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        this.kafkaConsumer = new KafkaConsumer<Integer,String>(properties);
    }

    @Override
    public void doWork() {
        kafkaConsumer.subscribe(Collections.singleton("city"));
        ConsumerRecords<Integer,String> records = kafkaConsumer.poll(1000);
        for(ConsumerRecord record:records){
            System.out.println("*********************************************************");
            System.out.println("topic: "+record.topic());
            System.out.println("partition: "+record.partition());
            System.out.println("key: "+record.key());
            System.out.println("value: "+record.value());
        }
    }


}
