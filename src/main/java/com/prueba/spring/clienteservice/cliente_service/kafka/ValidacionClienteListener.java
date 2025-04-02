package com.prueba.spring.clienteservice.cliente_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.prueba.spring.clienteservice.cliente_service.repository.ClienteRepository;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidacionClienteListener {
    private final ClienteRepository clienteRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "validar-cliente", groupId = "clientes-validator-group")
    public void validarCliente(ConsumerRecord<String, String> record) {
        String clienteId = record.value();
        Optional<Header> correlationHeader = Optional.ofNullable(record.headers().lastHeader("correlationId"));

        boolean existe = clienteRepository.existsById(Long.valueOf(clienteId));
        log.info("Validación de cliente ID {}: {}", clienteId, existe);

        correlationHeader.ifPresent(header -> {
            String correlationId = new String(header.value());
            ProducerRecord<String, String> response = new ProducerRecord<>("respuesta-validacion-cliente",
                    String.valueOf(existe));
            response.headers().add(new RecordHeader("correlationId", correlationId.getBytes()));
            kafkaTemplate.send(response);
        });
    }
}
