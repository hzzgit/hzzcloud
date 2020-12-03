//package com.hzz.hzzcloud.kafka;//
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//
//import net.fxft.cloud.metrics.Tps;
//import net.fxft.common.tpool.BlockedThreadPoolExecutor;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.kafka.core.ConsumerFactory;
//
//import javax.annotation.PreDestroy;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.LongAdder;
//
//public abstract class BaseKafkaConsumerhzz {
//    private static final Logger log = LoggerFactory.getLogger(BaseKafkaConsumerhzz.class);
//    @Autowired
//    private ConsumerFactory kafkaConsumerFactory;
//    private BlockedThreadPoolExecutor execPool = null;
//    private Thread listenThread;
//    private Thread checkPauseThread;
//    private volatile boolean stopped = false;
//    private volatile boolean paused = false;
//    private LongAdder totalPollCount = new LongAdder();
//    private LongAdder totalAcceptCount = new LongAdder();
//    private Tps processTps = new Tps();
//    private String logName = this.getClass().getName();
//
//    public BaseKafkaConsumerhzz() {
//    }
//
//    public void startListener(String consumerGroupId, String topic, int threadPoolSize) throws Exception {
//        this.startListener(consumerGroupId, Arrays.asList(topic), threadPoolSize);
//    }
//
//    public void startListener(String consumerGroupId, List<String> topicList, int threadPoolSize) throws Exception {
//        log.info("初始化KafkaComsumer！topicList=" + topicList + "; consumerGroupId=" + consumerGroupId + "; threadPoolSize=" + threadPoolSize);
//        this.execPool = new BlockedThreadPoolExecutor(threadPoolSize);
//        this.listenThread = new Thread(new Runnable() {
//            public void run() {
//                Consumer<String, byte[]> cos = BaseKafkaConsumerhzz.this.kafkaConsumerFactory.createConsumer(consumerGroupId, "");
//                cos.subscribe(topicList);
//
//                DO {
//                    try {
//                        if (BaseKafkaConsumerhzz.this.paused) {
//                            Thread.sleep(100L);
//                        } else {
//                            ConsumerRecords<String, byte[]> records = cos.poll(1000L);
//                            Iterator var3 = records.iterator();
//
//                            while(var3.hasNext()) {
//                                ConsumerRecord<String, byte[]> record = (ConsumerRecord)var3.next();
//                                BaseKafkaConsumerhzz.this.onReceiveData((String)record.key(), (byte[])record.value(), record);
//                            }
//                        }
//                    } catch (Exception var9) {
//                        BaseKafkaConsumerhzz.log.error("kafka poll出错！", var9);
//                    }
//                } while(!BaseKafkaConsumerhzz.this.stopped);
//
//                try {
//                    BaseKafkaConsumerhzz.this.execPool.shutdown();
//                } finally {
//                    return;
//                }
//
//            }
//        }, "kafka_poll");
//        this.listenThread.start();
//        this.checkPauseThread = new Thread(new Runnable() {
//            public void run() {
//                while(!BaseKafkaConsumerhzz.this.stopped) {
//                    try {
//                        boolean b = BaseKafkaConsumerhzz.this.isPausePoll();
//                        if (b != BaseKafkaConsumerhzz.this.paused) {
//                            BaseKafkaConsumerhzz.this.paused = b;
//                            BaseKafkaConsumerhzz.log.info("KafkaConsumer更新paused状态！logName=" + BaseKafkaConsumerhzz.this.logName + "; paused=" + BaseKafkaConsumerhzz.this.paused);
//                        }
//                    } catch (Exception var3) {
//                        BaseKafkaConsumerhzz.log.error("调用isPausePoll方法出错！", var3);
//                    }
//
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException var2) {
//                        var2.printStackTrace();
//                    }
//                }
//
//            }
//        });
//        this.checkPauseThread.start();
//    }
//
//    @PreDestroy
//    @Order(-2147483648)
//    public void shutdownHook() {
//        this.stopped = true;
//        log.info("---begin stop BaseKafkaConsumerhzz---" + this.logName);
//        long l1 = System.currentTimeMillis();
//
//        try {
//            if (this.execPool != null) {
//                this.execPool.awaitTermination(60L, TimeUnit.SECONDS);
//            }
//        } catch (InterruptedException var6) {
//            log.error("", var6);
//        }
//
//        try {
//            this.beforeShutdown();
//        } catch (Exception var5) {
//            log.error("beforeShutdown执行出错！", var5);
//        }
//
//        long l2 = System.currentTimeMillis();
//        log.info("---begin stop BaseKafkaConsumerhzz---" + this.logName + "; 耗时=" + (l2 - l1));
//    }
//
//    public abstract void beforeShutdown();
//
//    public abstract boolean isPausePoll();
//
//    public abstract boolean acceptData(String key);
//
//    public abstract void pocessData(String key, byte[] value, ConsumerRecord<String, byte[]> record);
//
//    public void onReceiveData(String key, byte[] value, ConsumerRecord<String, byte[]> record) {
//        this.execPool.submit(() -> {
//            this.totalPollCount.increment();
//            if (this.acceptData(key)) {
//                this.totalAcceptCount.increment();
//                this.pocessData(key, value, record);
//                this.processTps.inc();
//            }
//
//        });
//    }
//
//    public Tps getProcessTps() {
//        return this.processTps;
//    }
//
//    public long getTotalAcceptCount() {
//        return this.totalAcceptCount.longValue();
//    }
//
//    public long getTotalPollCount() {
//        return this.totalPollCount.longValue();
//    }
//
//    public void resetMonitorCount() {
//        this.totalAcceptCount.reset();
//        this.totalPollCount.reset();
//    }
//}
