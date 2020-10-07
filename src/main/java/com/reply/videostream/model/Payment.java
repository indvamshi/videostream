package com.reply.videostream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reply.videostream.validator.CheckCreditCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    private Long id;

    @CheckCreditCard(value = 16)
    private String ccNumber;

    @Min(value = 100, message = "Amount must be 3 digits")
    @Max(value = 999, message = "Amount must be 3 digits")
    private Integer amount;

    public Payment(String ccNumber, Integer amount) {
        this.ccNumber = ccNumber;
        this.amount = amount;
    }

}
