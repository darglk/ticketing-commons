package com.darglk.ticketingcommons.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Model containing credentials of a given user.
 *
 * @author darglk
 *
 */
@Data
@NoArgsConstructor
public class UserLoginRequestModel {

    /**
     * email - login to the application.
     */
    @Email(message = "email must be valid")
    @NotBlank
    private String email;

    /**
     * password - non hashed password value.
     */
    @NotBlank
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;
}
