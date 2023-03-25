package com.example.had.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fieldworkers")
public class FieldWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fwId;

    private String fname;
    private String lname;
    private String gender;
    private String DOB;
    private String phoneNo;
    private String address;
    private String registrationDate;

    public Optional<Object> stream() {
        return null;
    }


}
