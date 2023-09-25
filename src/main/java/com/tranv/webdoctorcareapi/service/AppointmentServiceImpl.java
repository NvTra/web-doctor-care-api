package com.tranv.webdoctorcareapi.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.tranv.webdoctorcareapi.dto.createAppointmentDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.entity.Doctor;
import com.tranv.webdoctorcareapi.entity.Patients;
import com.tranv.webdoctorcareapi.entity.Status;
import com.tranv.webdoctorcareapi.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientsService patientsService;

    @Autowired
    StatusService statusService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public Appointment findAppointmentById(int appointmentId) {
        return appointmentRepository.findAppointmentById(appointmentId);
    }

    // Book an appointment
    @Override
    public void bookAppointment(createAppointmentDTO dto) {
        // Create a new Appointment instance
        Appointment appointment = new Appointment();
        appointment.setStatus(0);
        appointment.setDate(dto.getAppointmentDate());
        appointment.setTime(dto.getAppointmentTime());
        appointment.setReasonExamination(dto.getReason());
        appointment.setPrice(dto.getConsultationFee());
        // Find the doctor based on the doctorId
        Doctor doctor = doctorService.findDoctorById(dto.getDoctorId());
        appointment.setDoctor(doctor);
        // Find the patient based on the patientId
        Patients patients = patientsService.findPatientsById(dto.getPatientId());
        appointment.setPatients(patients);
        // Save the appointment to the database
        appointmentRepository.save(appointment);
    }

    // Find appointments by patientId
    @Override
    public List<Appointment> findAppointmentByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Find appointments by doctorId
    @Override
    public List<Appointment> findAppointmentByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Accept an appointment
    @Override
    public void acceptAppointment(int appointmentId) {
        // Find the appointment based on the appointmentId
        Appointment appointment = appointmentRepository.getById(appointmentId);
        if (appointment.getStatuses() != null) {
            // Clear the old status (if any)
            appointment.setStatuses(null);
        }
        // Set the status to 1
        appointment.setStatus(1);
        // Save the updated appointment to the database
        appointmentRepository.save(appointment);
    }
    // cancel an appointment
    @Override
    public void cancelAppointment(int appointmentId, Status status) {
        // Find the appointment based on the appointmentId
        Appointment appointment = appointmentRepository.getById(appointmentId);
        // Set the cancellation status
        appointment.setStatuses(status);
        // Save the updated appointment to the database
        appointmentRepository.save(appointment);
    }
    //Create a PDF FILE
    @Override
    public byte[] generatePdfFile(Appointment appointment) throws DocumentException {
        Document document = new Document(PageSize.A5.rotate());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            //create PDF file
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            //Create a Thymeleaf context with data
            Context context = new Context();
            context.setVariable("data", appointment);
            // Process the HTML template with Thymeleaf and get the processed results
            String processedHTML = templateEngine.process("PDF.html", context);
            // Convert the processed HTML string to an input stream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(processedHTML.getBytes(StandardCharsets.UTF_8));
            // Use XMLWorkerHelper to parse the HTML and add it to the PDF document
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream);
            document.close();
            writer.close();
            // Returns the contents of the PDF file as a byte array
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DocumentException("Có lỗi xảy ra khi tạo file PDF " + e.getMessage());
        }

    }
}
