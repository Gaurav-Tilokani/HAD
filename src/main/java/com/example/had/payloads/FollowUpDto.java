package com.example.had.payloads;

import com.example.had.entities.FieldWorkerInHospital;
import com.example.had.entities.Visit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowUpDto {

    private int followUpId;

    private String followUpDate;

    private boolean urgentFlag;

    private int isActive;

    private String taskAssignedByDoctor;

    private String reviewByFieldWorker;

    private Visit visit;

    private String verificationNumber;

    private FieldWorkerInHospital fieldWorkerInHospital;
}