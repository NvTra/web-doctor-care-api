package com.tranv.webdoctorcareapi.service;


import com.tranv.webdoctorcareapi.dto.*;
import com.tranv.webdoctorcareapi.entity.*;

import com.tranv.webdoctorcareapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UsersServiceImpl implements UserDetailsService, UsersService {
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private StatusService statusService;

    public UsersServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save a user
    @Override
    public void saveUser(Users users) {
        Roles roles = rolesService.getRoleById(2);
        users.setRoles(roles);
        users.setActive(true);
        String pass = users.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setPassword(passwordEncoder.encode(pass));
        userRepository.save(users);
    }

    // Check if the email already exists
    @Override
    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Find a user by email
    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find a user by ID and convert to UserDTO
    @Override
    public UserDTO findUserById(int userId) {
        Optional<Users> result = userRepository.findById(userId);
        return result.map(this::toUserDTO).orElse(null);
    }

    // Find doctors by specializations name and convert to DoctorDTO list
    @Override
    public List<DoctorDTO> findBySpecializationsName(String specialtyName) {
        List<Doctor> doctors = userRepository.findBySpecializationsName(specialtyName);
        return doctors.stream().map(this::convert).toList();
    }

    // Create a patient
    @Override
    public void createPatient(CreatePatientDTO patientDTO) {
        Roles roles = rolesService.getRoleById(2);
        Users newUser = toUserEntity(patientDTO);
        newUser.setActive(true);
        newUser.setRoles(roles);
        //Saves the user to the userRepository.
        userRepository.save(newUser);
        //Saves the patient entity, created by converting the patientDTO, using the patientsService.
        patientsService.save(toPatientEntity(patientDTO, newUser));
    }

    // Create a doctor
    @Override
    public void createDoctor(CreateDoctorDTO doctorDTO) {
        Roles roles = rolesService.getRoleById(3);
        Users newUser = toUserEntity(doctorDTO);
        newUser.setActive(true);
        newUser.setRoles(roles);
        //Saves the user to the userRepository.
        userRepository.save(newUser);
        //Saves the user to the userRepository.
        doctorService.saveDoctor(toDoctorEntity(doctorDTO, userRepository.findByEmail(newUser.getEmail())));
    }

    // Unlock a patient
    @Override
    public void unlockPatient(int patientId) {
        Users users = patientsService.findUserByPatientId(patientId);
        //unlocks the user by calling the lockUser method.
        lockUser(users);
        userRepository.save(users);
        Patients patients = patientsService.findPatientsById(patientId);
        patients.setStatus(null);
        //Saves the updated patient using the patientsService.
        patientsService.save(patients);
    }

    // Lock a patient
    @Override
    public void lockPatient(int patientId, Status status) {
        Users users = patientsService.findUserByPatientId(patientId);
        //Locks the user by calling the lockUser method.
        lockUser(users);
        userRepository.save(users);
        statusService.saveStatus(status);
        Patients patients = patientsService.findPatientsById(patientId);
        patients.setStatus(status);
        //Saves the updated patient using the patientsService.
        patientsService.save(patients);

    }

    // Unlock a doctor
    @Override
    public void unlockDoctor(int doctorId) {
        Users users = doctorService.findUserByDoctorId(doctorId);
        //unocks the user by calling the lockUser method.
        lockUser(users);
        userRepository.save(users);
        Doctor doctor = doctorService.findDoctorById(doctorId);
        doctor.setStatus(null);
        doctorService.saveDoctor(doctor);
    }

    // Lock a doctor with a specific status
    @Override
    public void lockDoctor(int doctorId, Status status) {
        Users users = doctorService.findUserByDoctorId(doctorId);
        //Locks the user by calling the lockUser method.
        lockUser(users);
        userRepository.save(users);
        statusService.saveStatus(status);
        Doctor doctor = doctorService.findDoctorById(doctorId);
        doctor.setStatus(status);
        doctorService.saveDoctor(doctor);
    }

    // Lock/Unlock a user
    private void lockUser(Users users) {
        users.setActive(!users.isActive());
    }

    // Convert CreateUserDTO to Users entity
    private Users toUserEntity(CreateUserDTO dto) {
        Users newUser = new Users();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setAddress(dto.getAddress());
        newUser.setPhone(dto.getPhone());
        newUser.setGender(dto.getGender());
        newUser.setDescription(dto.getDescription());
        return newUser;
    }

    // Convert Users to UserDTO
    private UserDTO toUserDTO(Users users) {
        UserDTO dto = new UserDTO();
        dto.setId(users.getId());
        dto.setName(users.getName());
        dto.setEmail(users.getEmail());
        dto.setPhone(users.getPhone());
        dto.setGender(users.getGender());
        dto.setAvatar(users.getAvatar());
        dto.setDescription(users.getDescription());
        return dto;
    }

    // Convert CreatePatientDTO to Patients entity
    private Patients toPatientEntity(CreatePatientDTO createPatientDTO, Users users) {
        Patients patients = new Patients();
        patients.setDateOfBirth(createPatientDTO.getDateOfBirth());
        patients.setUsers(users);
        return patients;
    }

    // Convert Doctor to DoctorDTO
    private DoctorDTO convert(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setName(doctor.getUsers().getName());
        dto.setAddress(doctor.getUsers().getAddress());
        dto.setDescription(doctor.getUsers().getDescription());
        dto.setId(doctor.getId());
        dto.setPhone(doctor.getUsers().getPhone());
        dto.setSpecializationsName(doctor.getSpecializations().getName());
        return dto;
    }

    // Convert CreateDoctorDTO to Doctor entity
    private Doctor toDoctorEntity(CreateDoctorDTO dto, Users users) {
        Doctor newDoctor = new Doctor();
        newDoctor.setUsers(users);
        newDoctor.setTraining(dto.getTraining());
        newDoctor.setAchievements(dto.getAchievements());
        newDoctor.setSpecializations(specialtyService.getSpecializationByName(dto.getSpecializationsName()));
        return newDoctor;
    }

    // Load a user by username for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().getName())
                .build();
    }
}
