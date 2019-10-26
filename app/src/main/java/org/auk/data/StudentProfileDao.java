package org.auk.data;

import org.auk.models.StudentProfile;
import org.auk.utils.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentProfileDao implements BaseDao<StudentProfile> {
    private Session session = null;
    @Override
    public StudentProfile getById(int id) {
        StudentProfile studentProfile = null;
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            studentProfile =  session.find(StudentProfile.class,id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
        return studentProfile;
    }

    @Override
    public void save(StudentProfile studentProfile) {
        try (var sessionFactory = DbUtil.getSessionFactory()) {
             session = sessionFactory.getCurrentSession();
             session.getTransaction().commit();
            session.save(studentProfile);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
    }

    @Override
    public void update(StudentProfile studentProfile) {

    }

    @Override
    public void delete(StudentProfile studentProfile) {

    }

    @Override
    public List<StudentProfile> getAll() {
        return null;
    }
}
