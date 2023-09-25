package com.tranv.webdoctorcareapi;

import com.tranv.webdoctorcareapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebDoctorcareApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDoctorcareApiApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        var v = usersRepository.test("t");
//        System.out.println(v.get(0).getUsers().getName());
//    }
//// implements CommandLineRunner
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        var test = usersRepository.findBySpecializationsName("tim");
//        System.out.println(test);
//    }
}
