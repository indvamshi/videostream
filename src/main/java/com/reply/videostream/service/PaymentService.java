package com.reply.videostream.service;

import com.reply.videostream.model.Payment;

/**
 * Service class to add {@code Payment} details
 *
 */
public interface PaymentService {

    /**
     * Stores Payment Details,
     * @param payment details
     * @return new stored {@code Payment}
     */
    Payment addPayment(Payment payment);

}
