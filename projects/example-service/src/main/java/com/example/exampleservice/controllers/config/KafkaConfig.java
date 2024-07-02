package com.example.exampleservice.controllers.config;

import com.example.exampleservice.models.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    private Map<String,Object> consumerProps(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<Integer, List<Product>> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, List<Product>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer, List<Product>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
