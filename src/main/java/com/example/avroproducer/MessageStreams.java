package com.example.avroproducer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageStreams {

    String INPUT_STREAM = "inputStream";
    String OUTPUT = "output";

    @Input
    MessageChannel inputStream();

    @Output(OUTPUT)
    MessageChannel output();
}
