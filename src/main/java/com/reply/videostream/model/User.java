package com.reply.videostream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reply.videostream.validator.CheckCreditCard;
import com.reply.videostream.validator.CheckPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "username cannot be empty and should be alphanumerical")
    @NotEmpty
    private String userName;

    @CheckPassword
    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email(message = "Email must be in a valid format")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Dob cannot be empty")
    private String dob;

    @CheckCreditCard(value = 16)
    private String ccNumber;

    public User(String userName, String password, String email, String dob, String ccNumber) {
        this(userName, password, email,dob);
        this.ccNumber = ccNumber;
    }

    public User(String userName, String password, String email, String dob) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

}
