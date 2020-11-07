package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    StudentService studentList;
    private List<Team> teamList = new ArrayList<>();

    public List<Team> findAll(){
        return teamList;
    }

    public Team findById(int id){
        return teamList.get(id);
    }

    public void updateTeamName(int id, String name){
        Team orginTeam=findById(id-1);
        orginTeam.setName(name);
        teamList.remove(id-1);
        teamList.add(id-1,orginTeam);
    }

    public List<Team> teamStudents(List<Student> studentList){
        Collections.shuffle(studentList);
        int TeamSize = studentList.size()/6;
        int restNum = studentList.size()%6;
        for (int i = 0; i < 6; i++) {
            List<Student> tmp = new ArrayList<>();
            Team team =new Team();
            boolean flag=true;
            for (int k = 0; k < TeamSize ; k++) {
                tmp.add(studentList.get(i *  TeamSize + k));
                if(restNum>0&&flag==true)
                {
                    tmp.add(studentList.get(TeamSize*6 +i));
                    restNum--;
                    flag=false;
                }
            }
            String teamName = "team " + (i + 1);
            team.setId(i+1);
            team.setName(teamName);
            team.setStudentList(tmp);
            teamList.add(team);
        }
        return teamList;
    }
}
