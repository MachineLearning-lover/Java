package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("client")
public class Client extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String noticeTimeBeforeAppointment;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client(String firstName, String lastName, String middleName, String email, String passwordHash) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.MiddleName = middleName;
        this.email = email;
        this.passwordHash = passwordHash;
        appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }

    public void dropAppointment(Appointment appointment){
        this.appointments.remove(appointment);
    }

    public void editAppointment(Appointment toBeEditedAppointment, Appointment newAppointmentDetails){
        this.dropAppointment(toBeEditedAppointment);
        this.addAppointment(newAppointmentDetails);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", noticeTimeBeforeAppointment='" + noticeTimeBeforeAppointment + '\'' +
                ", appointments=" + appointments +
                ", id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    //    public Client(String firstName, String lastName, String email, String passwordHash) {
//        this.FirstName = firstName;
//        this.LastName = lastName;
//        this.email = email;
//        this.passwordHash = passwordHash;
//    }
}
