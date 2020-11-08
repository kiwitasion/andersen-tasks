package hibernate.actions;

import hibernate.models.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationReview {

    Session session;
    SessionFactory sf;

    public OperationReview(SessionFactory sf) {
        this.sf = sf;
    }

    public Review findReviewById(
            Long id){
        return sf.openSession().get(Review.class, id);
    }

    public void createReview(Review review){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(review);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in creating review "
                    + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

    public void updateReview(Review review){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(review);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in creating users"
                    + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

    public void deleteReviewById(Long id){
        try {
            session = sf.openSession();
            session.beginTransaction();

            Review review = findReviewById(id);
            session.delete(review);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in creating users"
                    + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
    }
}
