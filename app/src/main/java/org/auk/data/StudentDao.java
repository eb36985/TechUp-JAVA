package org.auk.data;

import org.auk.models.Student;
import org.auk.utils.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements BaseDao<Student> {

    private  Session session = null;
    @Override
    public Student getById(int id) {
        Student student = null;
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            student =  session.find(Student.class,id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
        return student;
    }

    public void save(Student student) {
        try (var sessionFactory = DbUtil.getSessionFactory()) {
             session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            session.save(student);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
    }


    @Override
    public void update(Student student) {
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            session.update(student);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
        Transaction transaction = null;

        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
         session.delete(student);
            transaction.commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }

    }

    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();


        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
            session.getTransaction().begin();
            studentList = session.createQuery("from Student", Student.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }

        return studentList;
    }

}
