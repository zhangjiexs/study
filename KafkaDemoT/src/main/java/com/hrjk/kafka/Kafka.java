package com.hrjk.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Kafka {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
            props.put("bootstrap.servers", "192.168.11.20:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size",16384);
        props.put("linger.ms", 5);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        int count = 0 ;
        for (int i = 0; i < 100000; i++) {
            ProducerRecord rr = new ProducerRecord<String, String>("nifitest", "a","b"+i);

            Future send = producer.send(rr);
            Object o = send.get();
            System.out.println(o);
            System.out.println(rr +"             " + count++ );
        }
        producer.close();
    }

}