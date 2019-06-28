package com.example.avroproducer;

import com.example.avroproducer.model.AvroHttpRequest;
import com.example.avroproducer.model.ClientIdentifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@EnableBinding(Source.class)
public class ProducerController {

    private SerDes serDes;
    private MessageChannel output;

    public ProducerController(SerDes serDes, MessageChannel output) {
        this.serDes = serDes;
        this.output = output;
    }

    @GetMapping("/")
    public String sendMessage(){
        AvroHttpRequest avroHttpRequest = createAvroHttpRequest();
        byte[] serializedRecord = serDes.serializeSpecificToBinary(avroHttpRequest);
        output.send(MessageBuilder.withPayload(serializedRecord).build());

        return "";
    }

    private AvroHttpRequest createAvroHttpRequest() {
        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostName("Jessica")
                .setIpAddress("this is where I live")
                .build();
        AvroHttpRequest avroHttpRequest = AvroHttpRequest.newBuilder()
                .setClientIdentifier(clientIdentifier)
                .setEmployeeNames(Arrays.asList("Jess", "Tim", "Mrin"))
                .setRequestTime(10L)
                .build();

        return avroHttpRequest;
    }
}
