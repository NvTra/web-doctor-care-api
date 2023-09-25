package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.dto.createAppointmentDTO;
import com.tranv.webdoctorcareapi.dto.PatientDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.entity.Patients;
import com.tranv.webdoctorcareapi.entity.Users;
import com.tranv.webdoctorcareapi.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PatientsServiceImpl implements PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;


    public PatientsServiceImpl(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @Override
    public Patients findPatientsById(int theId) {
        return patientsRepository.findPatientById(theId);
    }

    @Override
    public Patients getPatientsByUser(int userId) {
        return patientsRepository.getPatientsByUsers(userId);
    }

    @Override
    public List<PatientDTO> getPatientsByDoctor(int doctorId) {
        List<Patients> patients = patientsRepository.getPatientsByDoctor(doctorId);
        // Convert patient list to DTO list
        return patients.stream().map(this::patientToDto).toList();
    }


    @Override
    public Users findUserByPatientId(int patientId) {
        return patientsRepository.findUserByPatientId(patientId);
    }

    @Override
    public void save(Patients patients) {
        patientsRepository.save(patients);
    }

    // Method to convert patient to DTO
    private PatientDTO patientToDto(Patients patients) {
        // Create a DTO list of appointments and clinical descriptions
        List<createAppointmentDTO> appointmentDTOS = patients.getAppointments().stream().map(this::covertToDTO).toList();
        List<String> descriptionPathology = new ArrayList<>();
        // Loop through appointments to get information describing the condition
        for (createAppointmentDTO appointmentDTO : appointmentDTOS) {
            descriptionPathology.add(appointmentDTO.getReason());
        }
        // Create patient DTO and set information from patient and related user objects
        PatientDTO dto = new PatientDTO();
        dto.setId(patients.getId());
        dto.setName(patients.getUsers().getName());
        dto.setGender(patients.getUsers().getGender());
        dto.setAddress(patients.getUsers().getAddress());
        dto.setPhone(patients.getUsers().getPhone());
        dto.setDescriptionPathology(descriptionPathology);
        return dto;
    }

    // Method to convert appointment to DTO
    private createAppointmentDTO covertToDTO(Appointment appointment) {
        createAppointmentDTO dto = new createAppointmentDTO();
        dto.setReason(appointment.getReasonExamination());
        return dto;
    }
}
