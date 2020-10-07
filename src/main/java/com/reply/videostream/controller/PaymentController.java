package com.reply.videostream.controller;

import com.reply.videostream.model.Payment;
import com.reply.videostream.service.PaymentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * RESTful api for adding payment details {@code Payment}
 *
 */
@RestController
@RequestMapping("api")
public class PaymentController {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> addPayment(@RequestBody @Valid Payment payment) {
        LOGGER.debug("adding payment details");
        Payment newPayment = paymentService.addPayment(payment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPayment.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
