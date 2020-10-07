package com.reply.videostream.controller;

import com.reply.videostream.model.Payment;
import com.reply.videostream.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("CRUD operations on User")
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeAll
    public static void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void addPaymentDetails() {
        paymentController = new PaymentController(paymentService);

        Payment payment = new Payment("123456781012131", 215);

        Mockito.when(paymentService.addPayment(payment)).thenReturn(payment);

        ResponseEntity<Payment> paymentResponse = paymentController.addPayment(payment);

        Assertions.assertEquals(201, paymentResponse.getStatusCodeValue());
        Assertions.assertAll(
                () -> assertEquals("123456781012131", payment.getCcNumber()),
                () -> assertEquals(215, payment.getAmount()));
    }
}
