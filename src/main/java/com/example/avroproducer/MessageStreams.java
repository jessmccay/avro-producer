package com.example.avroproducer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface MessageStreams {

    String INPUT_STREAM = "inputStream";

    @Input
    MessageChannel inputStream();
}
