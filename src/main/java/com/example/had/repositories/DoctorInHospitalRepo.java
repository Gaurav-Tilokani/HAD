package com.example.had.repositories;

import com.example.had.entities.Doctor;
import com.example.had.entities.DoctorInHospital;

import com.example.had.entities.Hospital;
import com.example.had.entities.Visit;
import com.example.had.payloads.DoctorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorInHospitalRepo extends JpaRepository<DoctorInHospital, Integer> {
    public List<DoctorInHospital>  findAllByHospital(Hospital hospital);



}

