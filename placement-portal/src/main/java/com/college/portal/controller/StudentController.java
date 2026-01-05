package com.college.portal.controller;

import com.college.portal.model.Student;
import com.college.portal.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    // LIST + SEARCH
    @GetMapping
    public String listStudents(@RequestParam(value = "keyword", required = false) String keyword,
                               Model model) {

        List<Student> students = (keyword != null)
                ? studentRepo.findByNameContainingIgnoreCase(keyword)
                : studentRepo.findAll();

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // ADD FORM
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // SAVE (ADD + UPDATE)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/students";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentRepo.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "student-form";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/students";
    }
}
