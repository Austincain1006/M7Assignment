package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Worker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class MyController {
    @GetMapping("/behrend/{state}")
    public String behrend(@PathVariable String state, Model model){
        Student[] sa = new Student[2];
        sa[0] = new Student();
        sa[0].setName("John");
        sa[0].setAge(10);
        sa[0].setPhone("1111111");
        sa[1] = new Student();
        sa[1].setName("Mary");
        sa[1].setAge(12);
        sa[1].setPhone("2222111");
        model.addAttribute("students", sa);
        model.addAttribute("state", state);
        return "behrend";
    }

    @GetMapping("/worker/sex/{gender}")
    public String worker(@PathVariable String gender, Model model){
        Worker[] workers_new = new Worker[5];
        try {
            File file = new File("C:\\Temp\\GetMapping_Demo\\demo3\\src\\main\\resources\\workerFile.txt");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            workers_new = (Worker[]) o;
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println( "BROKE" );
        }

        model.addAttribute("workers", workers_new);
        model.addAttribute("gender", gender);

        return "ageView";
    }

    @GetMapping("/worker/create")
    public String worker(){
        
        File file = new File("C:\\Temp\\GetMapping_Demo\\demo3\\src\\main\\resources\\workerFile.txt");
        Worker[] workers = new Worker[5];
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject( workers );
            oos.close();
            fos.close();


        } catch (Exception e) {
            
        }

        return "workerCreate";
    }
}
