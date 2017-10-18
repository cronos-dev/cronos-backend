package com.cronos.requestBody;

/**
 * Created by toshikijahja on 7/29/17.
 */
public class CreateUserRequestBody {

    private String firstName;
    private String lastName;

    public CreateUserRequestBody() {

    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
