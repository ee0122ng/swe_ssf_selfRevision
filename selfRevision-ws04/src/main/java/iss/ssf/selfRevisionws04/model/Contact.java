package iss.ssf.selfRevisionws04.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact implements Serializable {

    private String id;

    @NotEmpty(message="Mandatory field")
    @Size(min=3, max=64, message="Name length must be between 3 and 64")
    private String name;

    @NotEmpty(message="Mandatory field")
    @Email(message="Invalid email format")
    private String email;

    @NotEmpty(message="Mandatory field")
    @Pattern(regexp="[0-9]{7}", message="Phone number must be of length 7")
    private String phoneNumber;
    
    @NotNull(message="Mandatory field")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Min(value=10, message="Age must not below 10 years old")
    @Max(value=100, message="Age must not above 100 years old")
    private Integer age;

    public Contact() {
        id = generateId(8);
    }

    public Contact(String id, String name, String email, String phoneNumber, LocalDate birthDate) {
        this.id = generateId(8);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {

        Integer age = 0;

        if (null != birthDate) {
            age = Period.between(birthDate, LocalDate.now()).getYears();
        }

        this.age = age;
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return this.age;
    }

    public synchronized String generateId(Integer numberChar) {

        Random rand = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < numberChar) {
            sb.append(Integer.toHexString(rand.nextInt()));
        }

        return sb.toString().substring(0, numberChar);
    }
    
}
