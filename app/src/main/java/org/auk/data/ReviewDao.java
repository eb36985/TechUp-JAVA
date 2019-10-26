package org.auk.data;


import org.auk.models.Review;
import org.auk.models.Student;
import org.auk.utils.DbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements BaseDao<Review> {
    Session session = null;

    @Override
    public Review getById(int id) {
        Review review = null;
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            review =  session.find(Review.class,id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.getStackTrace();
        }
        return review;
    }

    @Override
    public void save(Review review) {
        try (var sessionFactory = DbUtil.getSessionFactory()) {
            session=  sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            session.save(review);
            System.out.println("===========Saved Review==============");
            session.getTransaction().commit();
        } catch (HibernateException e) {

            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void update(Review review) {

    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public List<Review> getAll() {
        List<Review> reviewList = new ArrayList<>();


        try (var session = DbUtil.getSessionFactory().getCurrentSession()) {
             session.getTransaction().begin();
            reviewList = session.createQuery("from Review", Review.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }

        return reviewList;
    }
}
