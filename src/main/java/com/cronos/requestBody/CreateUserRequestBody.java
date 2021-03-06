package com.cronos.requestBody;

/**
 * Created by toshikijahja on 7/29/17.
 */
public class CreateUserRequestBody {

    private String firstName;
    private String lastName;
    private String email;
    private Long birthday;
    private String gender;
    private String token;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public long getBirthday() {
        return this.birthday;
    }

    public void setBirthday(final Long birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
