package org.auk.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    int id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private Date startDate;
    private Date endDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp")
    private Date createdAt;
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns =@JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentList;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    List<Review> reviewList;

    public void addReview(Review review){
        if(reviewList == null){
            reviewList = new ArrayList<>();
        }else {
            review.setCourse(this);
            reviewList.add(review);

        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
