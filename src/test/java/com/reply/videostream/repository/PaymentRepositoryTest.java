package com.reply.videostream.repository;

import com.reply.videostream.model.Payment;
import com.reply.videostream.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CRUD Operations on User Registration")
public class PaymentRepositoryTest {

    @Test
    @DisplayName("save payment details")
    void savePayment() {

        Payment payment = new Payment("123456781012131", 215);

        RegistrationRepository repo = new RegistrationRepository();
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                             "1989-07-16T19:23:51", "123456781012131");

        repo.register(user);

        PaymentRepository paymentRepo = new PaymentRepository(repo);

        Assertions.assertTrue(paymentRepo.savePayment(payment));

    }

    @Test
    @DisplayName("invalid payment details")
    void invalidPayment() {

        Payment payment = new Payment("", 215);

        RegistrationRepository repo = new RegistrationRepository();
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                             "1989-07-16T19:23:51");

        repo.register(user);

        PaymentRepository paymentRepo = new PaymentRepository(repo);

        Assertions.assertFalse(paymentRepo.savePayment(payment));

    }
}
