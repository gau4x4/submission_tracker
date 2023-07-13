package com.gaurav.sub.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        final Optional<Student> studentByEmail = this.studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        final boolean exists = this.studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student doesn't exists!");
        }
        this.studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("Student with id: " + studentId + " doesn't exists!"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && ! Objects.equals(student.getEmail(), email)) {
            final Optional<Student> studentByEmail = this.studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent())
                throw new IllegalStateException("Email already taken");

            student.setEmail(email);
        }
    }
}
