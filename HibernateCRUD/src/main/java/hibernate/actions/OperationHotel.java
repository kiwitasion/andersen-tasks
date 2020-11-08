package hibernate.actions;

import hibernate.models.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationHotel {

    Session session;
    SessionFactory sf;

    public OperationHotel(SessionFactory sf) {
        this.sf = sf;
    }

    public Hotel findHotelById(Long id){
        return sf.openSession().get(Hotel.class, id);
    }

    public void createHotel(Hotel hotel){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(hotel);

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

    public void updateHotel(Hotel hotel){
        try {

            session = sf.openSession();
            session.beginTransaction();

            session.update(hotel);

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

    public void deleteUserById(Long id){
        try {
            session = sf.openSession();
            session.beginTransaction();

            Hotel hotel = findHotelById(id);
            session.delete(hotel);

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
