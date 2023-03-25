






package com.example.had.services.impl;

import com.example.had.entities.*;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.FieldWorkerDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;
import com.example.had.payloads.HospitalDto;
import com.example.had.payloads.SupervisorDto;
import com.example.had.repositories.FieldWorkerInHospitalRepo;
import com.example.had.repositories.FieldWorkerRepo;
import com.example.had.repositories.HospitalRepo;
import com.example.had.repositories.SupervisorRepo;
import com.example.had.services.SupervisorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private FieldWorkerRepo fieldWorkerRepo;

    @Autowired
    private FieldWorkerInHospitalRepo fieldWorkerInHospitalRepo;


    @Override
    public SupervisorDto createSupervisor(SupervisorDto supervisorDto) {
        Supervisor supervisor = this.modelMapper.map(supervisorDto, Supervisor.class);
        int hospitalId = supervisor.getHospital().getHospitalId();
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "Hospital Id", hospitalId));
        supervisor.setHospital(hospital);
        Supervisor savedSupervisor = this.supervisorRepo.save(supervisor);
        return this.modelMapper.map(savedSupervisor, SupervisorDto.class);
    }

    @Override
    public SupervisorDto updateSupervisor(SupervisorDto supervisorDto, Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("Supervisor", "supervisorId", supervisorId);
        });
        supervisor.setFname(supervisorDto.getFname());
        supervisor.setLname(supervisorDto.getLname());
        supervisor.setGender(supervisorDto.getGender());
        supervisor.setDOB(supervisorDto.getDOB());
        supervisor.setPhoneNo(supervisorDto.getPhoneNo());
        supervisor.setAddress(supervisorDto.getAddress());
        supervisor.setHospital(supervisorDto.getHospital());
        supervisor.setRegistrationDate(supervisorDto.getRegistrationDate());
        Supervisor updatedSupervisor = this.supervisorRepo.save(supervisor);
        return this.modelMapper.map(updatedSupervisor, SupervisorDto.class);
    }

    @Override
    public SupervisorDto getSupervisorById(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        return this.modelMapper.map(supervisor, SupervisorDto.class);
    }

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        List<Supervisor> supervisors = this.supervisorRepo.findAll();
        List<SupervisorDto> supervisorDtos = supervisors.stream().map(supervisor -> this.modelMapper.map(supervisor, SupervisorDto.class)).collect(Collectors.toList());
        return supervisorDtos;
    }

    @Override
    public void deleteSupervisor(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        this.supervisorRepo.delete(supervisor);
    }

    @Override
    public String getPhoneNo(Integer sId) {
        Supervisor supervisor = this.supervisorRepo.findById(sId).orElseThrow(() -> {
            return new ResourceNotFoundException("patient", "patientId", sId);
        });
        String phoneNo = supervisor.getPhoneNo();
        return phoneNo;
    }



    @Override
    public List<FieldWorkerInHospitalDto> getFieldWorker(Integer hospitalId) {

        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "Hospital Id", hospitalId));
         List<FieldWorkerInHospital> fieldWorkerInHospitals= this.fieldWorkerInHospitalRepo.findAllByHospital(hospital);
        List<FieldWorkerInHospitalDto> fieldWorkerInHospitalDtos = fieldWorkerInHospitals.stream().map(fieldWorkerInHospital -> this.modelMapper.map(fieldWorkerInHospital, FieldWorkerInHospitalDto.class)).collect(Collectors.toList());
        return fieldWorkerInHospitalDtos;

    }
}







//    @Override
//    public void registerFieldWorker(Integer fwId  , Integer hosId)
//    {
//        FieldWorkerInHospital  fieldWorkerInHospital=new FieldWorkerInHospital();
//        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(()->new ResourceNotFoundException("FieldWorker","fieldworker id",fwId));;
//        Hospital hospital = this.hospitalRepo.findById(hosId).orElseThrow(()->new ResourceNotFoundException("Hospital","hospital",hosId));;
//        fieldWorkerInHospital.setFieldWorker(fieldWorker);
//        fieldWorkerInHospital.setHospital(hospital);
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        fieldWorkerInHospital.setRegistrationDate(formatter.format(date));
//        FieldWorkerInHospital fieldWorkerInHospital1 = this.fieldWorkerInHospitalRepo.save(fieldWorkerInHospital);
//
//    }
//    @Override
//    public List<FieldWorkerDto> getFieldWorker(int hospitalId) {
//
//        FieldWorkerInHospital fieldWorkerInHospital= this.fieldWorkerInHospitalRepo.findById(hospitalId).orElseThrow(() -> {
//            return new ResourceNotFoundException("hospital", "hospitalId", hospitalId); });
//        FieldWorker fieldWorker=fieldWorkerInHospital.getFieldWorker();
//
//        List<FieldWorkerDto> fieldWorkerDtos = fieldWorker.stream().map(hospital -> this.modelMapper.map(fieldWorker, FieldWorkerDto.class)).collect(Collectors.toList());
//        return fieldWorkerDtos;
//       }




