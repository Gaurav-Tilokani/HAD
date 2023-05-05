package com.example.had.repositories;

import com.example.had.entities.DoctorInHospital;
import com.example.had.entities.Hospital;
import com.example.had.entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupervisorRepo extends JpaRepository<Supervisor, Integer> {
    List<Supervisor> findAllByHospital(Hospital hospital);
}
