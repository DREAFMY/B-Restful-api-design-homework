package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final String[] studentNames = {
            "成吉思汗", "鲁班七号", "太乙真人", "钟无艳", "花木兰", "雅典娜", "芈月", "白起", "刘婵",
            "庄周", "马超", "刘备", "哪吒", "大乔", "蔡文姬"
    };
    private List<Student> studentList = new ArrayList<>();

    public StudentService() {
        for (int i = 0; i < studentNames.length; i++) {
            String gender;
            if (i % 2 == 0) {
                gender = "female";
            } else
                gender = "male";
            studentList.add(new Student(i + 1, studentNames[i], gender, ""));
        }
    }

    public List<Student> getALL() {
        return studentList;
    }

    public void removeStudent(int id) {
        studentList.remove(id - 1);
    }

    public void createStudent(Student student)
    {
        int id = studentList.size()+1;
        Student newStudent = new Student(id ,student.getName(),student.getGender(),"");
        studentList.add(newStudent);
    }

    public Student findStudent(int id) {
        return studentList.get(id - 1);
    }

    public void updateStudent(Student student) {
        Student originStudent = findStudent(student.getId());
        if (student.getGender() != null)
            originStudent.setGender(student.getGender());
        if (student.getName() != null)
            originStudent.setGender(student.getName());
        if (student.getNote() != null)
            originStudent.setNote(student.getNote());
        studentList.remove(student.getId());
        studentList.add(student.getId(), originStudent);
    }

    public List<Student> findStudentByGender(String gender) {
        List<Student> res = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGender().equals(gender))
                res.add(student);
        }
        return res;
    }

}
