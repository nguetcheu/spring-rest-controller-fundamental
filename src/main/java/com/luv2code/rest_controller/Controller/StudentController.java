package com.luv2code.rest_controller.Controller;

import com.luv2code.rest_controller.Entity.Student;
import com.luv2code.rest_controller.Exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();
        students.add(new Student(1,"John","Doe","student@gmail.com"));
        students.add(new Student(2,"Noumangue","Charles","noucharles@gmail.com"));
        students.add(new Student(3,"Nguetcheu","Dominique","ngue@gmail.com"));
    }

    @GetMapping("/students")
    public List<Student> getListOfStudents() {
        return students;
    }

    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable() int studentID) {

        List<Student> studentList = this.students;

        if ( (studentID >= studentList.size()) || (studentID < 0) ) {
            throw new StudentNotFoundException("Student not found with ID: " + studentID);
        }

        /*Student tempStudent = students.stream().filter(
                x -> x.getId() == studentID
        ).findFirst().orElse(null);*/

        return studentList.get(studentID);
    }
}
