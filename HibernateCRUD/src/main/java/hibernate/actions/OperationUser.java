package hibernate.actions;

import hibernate.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.UnaryOperator;

public class OperationUser {

    Session session;
    SessionFactory sf;

    public OperationUser(SessionFactory sf) {
        this.sf = sf;
    }

    public User findUserById(Long id){
        return sf.openSession().get(User.class, id);
    }

    public void createUser(User user){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in creating users "
                    + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

    public void updateUser(User user){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in updating users "
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

            User user = findUserById(id);
            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e){
            System.err.println("Error in deleting users"
                    + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

}
