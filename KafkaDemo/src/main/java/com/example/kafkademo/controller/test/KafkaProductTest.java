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

import java.io.IOException;

/**
 * @author BaoFeng
 * @version 1.0
 * @date 2019/12/17 17:57
 */
public class KafkaProductTest {

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
