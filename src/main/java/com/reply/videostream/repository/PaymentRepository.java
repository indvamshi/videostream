package com.reply.videostream.repository;

import com.reply.videostream.model.Payment;
import com.reply.videostream.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access layer to store {@code Payment} details
 *
 */
@Repository("paymentRepository")
public class PaymentRepository {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final RegistrationRepository registrationRepository;

    @Autowired
    public PaymentRepository(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public boolean savePayment(Payment payment) {
        LOGGER.debug("Storing payment details into DB");
        List<User> users = registrationRepository.getAllUsersWithCCNumber();

        return users.stream().anyMatch(user -> user.getCcNumber().equals(payment.getCcNumber()));
    }

}
