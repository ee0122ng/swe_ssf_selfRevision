package iss.ssf.selfRevisionws03.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact {

    @NotNull(message="Mandatory field!")
    @Size(min=3, max=64, message="Name length must be between 3 and 64")
    private String name;

    @NotBlank(message="Mandatory field")
    @Email(message="Invalid email format")
    private String email;

    @NotNull(message="Mandatory field")
    @Pattern(regexp="[0-9]{7}", message="Phone number must be 7 digits")
    private String phoneNumber;

    @Min(value=10, message="Age must not below 10 years old")
    @Max(value=100, message="Age must not above 100 years old")
    private Integer age;

    @NotNull(message="Mandatory field")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message="Birth date must be in the past")
    LocalDate birthDate;

    private String id;

    public Contact() {
        this.id = generateId(8);

    }

    public Contact(String name, String email, String phoneNumber, LocalDate birthDate) {
        this.id = generateId(8);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
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

    public Integer getAge() {
        return this.age;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id = " + getId() + "\n" +
            "name = " + getName() + "\n" +
            "email = " + getEmail() + "\n" +
            "phoneNumber = " + getPhoneNumber() + "\n" +
            "birthDate = " + getBirthDate() + "\n" +
            "age = " + getAge() + "\n";
    }


    private synchronized String generateId(int numOfChars) {

        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while(sb.length() < numOfChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numOfChars);
    }

    
}
