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

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.Properties;

/**
 * KAFKA 生产者
 * @author Wakaka
 * @version 1.0
 * @date 2019/12/17 14:02
 */
public class Producer {

    private KafkaProducer<Integer,String> kafkaProducer;

    public Producer(){

        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.220.132:9092,192.168.220.133:9092,192.168.220.134:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<Integer,String>(properties);
    }

    public void sendMsg(){
        //创建消息
        //指定主题及消息本身
        ProducerRecord<Integer,String> record = new ProducerRecord<>("city","北京");
        //指定主题key、消息本身
        ProducerRecord<Integer,String> record2 = new ProducerRecord<>("city",1,"北京");
        //指定主题要写入的的patition

        for (int messageNo = 1; messageNo < 10; messageNo++){
            ProducerRecord<Integer,String> record3 = new ProducerRecord<>("city",1,1,"北京-"+messageNo);
            kafkaProducer.send(record3, new Callback() {
                //RecordMetadata 元数据 消息主题、key、消息本身
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("partition: "+recordMetadata.partition());
                    System.out.println("topic: "+recordMetadata.topic());
                    System.out.println("offset: "+recordMetadata.offset());
                }
            });
        }

    }

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer();
        for (int messageNo = 1; messageNo < 10; messageNo++){
            producer.sendMsg();
         }
        //发布过程是一个异步过程，所以不让主线程结束
        //否则消息无法发送
        //System.in.read();
    }
}
