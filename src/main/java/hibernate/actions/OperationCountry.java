package hibernate.actions;

import hibernate.models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationCountry {

    Session session;
    SessionFactory sf;

    public OperationCountry(SessionFactory sf) {
        this.sf = sf;
    }



    public Country findCountryById(Long id){
        return sf.openSession().get(Country.class, id);
    }

    public void createCountry(Country country){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(country);

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

    public void updateCountry(Country country){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(country);

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

            Country country = findCountryById(id);
            session.delete(country);

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
