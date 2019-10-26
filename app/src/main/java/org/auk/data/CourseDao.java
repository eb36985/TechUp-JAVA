package org.auk.data;

import org.auk.models.Course;
import org.auk.models.Student;
import org.auk.utils.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseDao implements BaseDao<Course> {
    Session session = null;

    @Override
    public Course getById(int id) {
        Course course = null;
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
            session.getTransaction().begin();
           course =  session.find(Course.class,id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
        return course;
    }

    @Override
    public void save(Course course) {
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
             session.getTransaction().begin();
            session.save(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
    }

    @Override
    public void update(Course course) {

    }

    @Override
    public void delete(Course course) {

    }

    @Override
    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();


        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            session.getTransaction().begin();
            courseList = session.createQuery("from Course ", Course.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }

        return courseList;
    }
}
