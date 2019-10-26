package org.auk.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "student" )
public class Student {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( updatable = false, nullable = false )
    private int id;
    private String firstName;
    private String lastName;
    @Column( unique = true )
    private String email;
    @Column( name = "phone_number" )
    private String phone;
    @Transient
    private @Gender
    int gender;
    private Date birthday;
    private String phoneNumber;

    @OneToOne(mappedBy = "student" ,cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_student",
            joinColumns =@JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courseList;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, @Gender int gender, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = dob;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                gender == student.gender &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(email, student.email) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(phoneNumber, student.phoneNumber);
    }

    public void addCourse(Course course){
        if(courseList == null){
            courseList = new ArrayList<>();
        }

        courseList.add(course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, gender, birthday);
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    enum Gender {
//        MALE,
//        FEMALE
//    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getGender() {
        return gender;
    }


    public void setGender(@Gender int gender) {
        this.gender = gender;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(Date dob) {
        this.birthday = dob;
    }

    @Override
    public String toString() {
        return Student.class + "\n [ID]: " + id + " [Name]: " + firstName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @interface Gender {
        int MALE = 1;
        int FEMALE = 2;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
