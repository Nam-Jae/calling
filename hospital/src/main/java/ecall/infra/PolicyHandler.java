package ecall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecall.config.kafka.KafkaProcessor;
import ecall.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    HospitalRepository hospitalRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Called'"
    )
    public void wheneverCalled_Book(@Payload Called called) {
        Called event = called;
        System.out.println("\n\n##### listener Book : " + called + "\n\n");

        // Sample Logic //
        Hospital.book(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CallCanceled'"
    )
    public void wheneverCallCanceled_BookCancel(
        @Payload CallCanceled callCanceled
    ) {
        CallCanceled event = callCanceled;
        System.out.println(
            "\n\n##### listener BookCancel : " + callCanceled + "\n\n"
        );

        // Sample Logic //
        Hospital.bookCancel(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
