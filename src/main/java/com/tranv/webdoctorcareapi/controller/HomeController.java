package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/")
    public String home(Model model) {
        Appointment appointment = appointmentService.findAppointmentById(1);
        model.addAttribute("data", appointment);
        return "PDF";
    }
}
