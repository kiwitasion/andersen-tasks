package hibernate.actions;

import hibernate.models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationOrder {

    SessionFactory sf;
    Session session;

    public OperationOrder(SessionFactory sf) {
        this.sf = sf;
    }

    public Order findOrderById(Long id){
        return sf.openSession().get(Order.class, id);
    }

    public void createOrder(Order order){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.save(order);

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

    public void updateOrder(Order order){
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(order);

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

    public void deleteOrderById(Long id){
        try {
            session.beginTransaction();

            Order order = findOrderById(id);
            session.delete(order);

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
