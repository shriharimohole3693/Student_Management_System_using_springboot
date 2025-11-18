package com.StudentsManagementSystem.controller;


import com.StudentsManagementSystem.entity.Student;
import org.springframework.ui.Model;
import com.StudentsManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private StudentService service;

    @GetMapping("/home")
    public String home() {
        return "home";  // view page html file -> home.html  //written in templete with same name
    }


    @GetMapping("/students") // should be same as client path and this method will execute when coming on this path
    public String getAllStudents(Model model) {  //Model = a data carrier

        // add data to the model
        model.addAttribute("students", service.getAllStudents());  // students = key which act as bag caries html page

        // return the HTML page name
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {  // key to object mapping
        service.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("students/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        Student student = new Student();
        model.addAttribute("student", service.getById(id));
        return "edit-student";
    }


    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student) {

        Student existingStudent = service.getById(id); // take existing student

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        service.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudentById(id); // take existing student
        return "redirect:/students";
    }



}



