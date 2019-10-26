package org.auk.models;

import javax.persistence.*;
import java.util.Objects;
import org.auk.models.*;

@Entity
@Table( name = "student_profile", schema = "tech-up" )
public class StudentProfile {

    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column( name = "address", nullable = true, length = 255 )
    private String address;

    @Basic
    @Column( name = "birthplace", nullable = true, length = 255 )
    private String birthplace;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProfile that = (StudentProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birthplace, that.birthplace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, birthplace);
    }
}
