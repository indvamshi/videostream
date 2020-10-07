package com.reply.videostream.service;

import com.reply.videostream.exception.InvalidPaymentDetailsException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Payment Service ")
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    @DisplayName("valid payment details")
    void addPaymentDetails() {
        paymentService = new PaymentServiceImpl(paymentRepository);

        Payment payment = new Payment("", 123);
        Mockito.when(paymentRepository.savePayment(payment)).thenReturn(true);

        Payment newPayment = paymentService.addPayment(payment);

        Assertions.assertNotNull(newPayment);

    }

    @Test
    @DisplayName("invalid payment details")
    void invalidPaymentDetails() {
        paymentService = new PaymentServiceImpl(paymentRepository);

        Payment payment = new Payment("", 123);
        Mockito.when(paymentRepository.savePayment(payment)).thenReturn(false);

        Assertions.assertThrows(InvalidPaymentDetailsException.class, () -> paymentService.addPayment(payment));
    }
}
