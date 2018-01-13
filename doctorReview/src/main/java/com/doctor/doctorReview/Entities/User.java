package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String FirstName;
    protected String LastName;
    protected String MiddleName;
    protected String email;
    protected String phone;
    protected String passwordHash;

    public User(String firstName, String lastName, String middleName, String email, String phone) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.MiddleName = middleName;
        this.email = email;
        this.phone = phone;
    }

    public User(User user){
        this.email = user.getEmail();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.MiddleName = user.getMiddleName();
        this.passwordHash = user.getPasswordHash();
        this.phone = user.getPhone();
        this.id = user.getId();
    }
}
