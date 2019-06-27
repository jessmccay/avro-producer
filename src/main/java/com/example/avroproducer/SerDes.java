package com.example.avroproducer;

import com.example.avroproducer.model.AvroHttpRequest;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.stereotype.Component;

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
}
