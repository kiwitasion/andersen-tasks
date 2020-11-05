package hibernate.actions;

import hibernate.models.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationTour {

    Session session;
    SessionFactory sf;

    public OperationTour(SessionFactory sf) {
        this.sf = sf;
    }

    public Tour findTourById(Long id) {
        return sf.openSession().get(Tour.class, id);
    }

    public void createTour(Tour tour){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(tour);

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

    public void updateTour(Tour tour){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(tour);

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

    public void deleteTourById(Long id){
        try {
            session = sf.openSession();
            session.beginTransaction();

            Tour tour = findTourById(id);
            session.delete(tour);

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
