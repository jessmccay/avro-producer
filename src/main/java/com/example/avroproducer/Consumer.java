package com.example.avroproducer;

import com.example.avroproducer.model.AvroHttpRequest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

@EnableBinding(MessageStreams.class)
public class Consumer {

    private SerDes serDes;

    public Consumer(SerDes serDes) {
        this.serDes = serDes;
    }

    @StreamListener(MessageStreams.INPUT_STREAM)
    public void streamHandler(byte[] messageRequest, @Headers MessageHeaders headers) {
        AvroHttpRequest request = serDes.deserializeToBinary(messageRequest);
        System.out.println(request.toString());
        System.out.println("Message Headers:  " + headers);
    }
}
