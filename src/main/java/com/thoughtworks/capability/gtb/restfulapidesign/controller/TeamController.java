package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Team>> groupStudent(){
        return ResponseEntity.ok(teamService.teamStudents(studentService.getALL()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Student>> getGroup(@PathVariable int id){
        return ResponseEntity.ok(teamService.findById(id).getStudentList());
    }

    @PutMapping
    public ResponseEntity changeName(@RequestParam("id")int id, @RequestParam("name")String name){
        teamService.updateTeamName(id,name);
        return ResponseEntity.ok().build();
    }
}
