package com.reply.videostream.service;

import com.reply.videostream.exception.InvalidPaymentDetailsException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.repository.PaymentRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public final class PaymentServiceImpl implements PaymentService {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Payment payment) {

        if(paymentRepository.savePayment(payment)) {
            return payment;
        }
        LOGGER.error(String.format("User doest not exist with card number %s",
                                   payment.getCcNumber()));
        throw new InvalidPaymentDetailsException(String.format("User doest not exist with card number %s",
                                                               payment.getCcNumber()));
    }

}
