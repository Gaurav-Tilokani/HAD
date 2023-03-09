package com.example.had.services;

import com.example.had.payloads.PatientDto;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDto);

    String getPhoneNo(Integer pId);
    
    int searchPatient(int patientId);
}