package com.controller;

import com.entity.Student;
import com.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;


    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepo.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<?> getStudent(@RequestParam("id") int id){

        return ResponseEntity.ok(studentRepo.getStudent(id));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllStudent(){

        return ResponseEntity.ok(studentRepo.getAllStudent());
    }


    @GetMapping("clearAll")
    public ResponseEntity<?> clearAllCache(){

        return ResponseEntity.ok(studentRepo.clearAllCache());
    }


}
