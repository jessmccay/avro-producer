package com.example.avroproducer;

import com.example.avroproducer.model.AvroHttpRequest;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class SerDes {

    public byte[] serializeSpecificToBinary(AvroHttpRequest specificRecord){
        DatumWriter<AvroHttpRequest> writer = new SpecificDatumWriter<>(AvroHttpRequest.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] serializedRecord = new byte[0];
        Encoder binaryEncoder = EncoderFactory.get()
                .binaryEncoder(outputStream, null);
        try {
            writer.write(specificRecord, binaryEncoder);
            binaryEncoder.flush();
            serializedRecord = outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return serializedRecord;
    }


    public AvroHttpRequest deserializeToBinary(byte[] messageRequest) {
        DatumReader<AvroHttpRequest> reader = new SpecificDatumReader<>(AvroHttpRequest.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(messageRequest);
        Decoder decoder = DecoderFactory.get()
                .binaryDecoder(inputStream, null);
        try {
           return reader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
