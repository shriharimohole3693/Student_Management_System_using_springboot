package com.StudentsManagementSystem.repository;

import com.StudentsManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Studentrepository extends JpaRepository<Student,Integer> { // < Entity classname, Datatype of ID>

}
