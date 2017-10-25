package sample.persistence;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import sample.entity.UsersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulawaite on 2/2/16.
 */
public class UsersDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all users
     *
     * @return All users
     */
    public List<UsersEntity> getAllUsers() {
        List<UsersEntity> users = new ArrayList<UsersEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            users = session.createCriteria(UsersEntity.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all users", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    /** Get a single user for the given id
     *
     * @param id user's id
     * @return User
     */
    public UsersEntity getUser(int id) {
        UsersEntity user = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            user = (UsersEntity) session.get(UsersEntity.class, id);
        } catch (HibernateException he) {
            log.error("Error getting user with id: " + id, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    /** Retrieve users by lastname
     *
     * @param lastName User's last name which is the search criteria
     * @return User
     */
    public List<UsersEntity> getUsersByLastName(String lastName) {
        List<UsersEntity> users = new ArrayList<UsersEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(UsersEntity.class);
            criteria.add(Restrictions.eq("lastName", lastName));
            users = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting all users with last name: " + lastName, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    /** save new user
     * @param user user to insert
     * @return id of the inserted user
     */

    public int insert(UsersEntity user) {
        log.error(user.toString());
        int id = 0;
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            id = (int) session.save(user);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user: " + user, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /** save  user
     * @param user user to update
     */

    public void update(UsersEntity user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user: " + user, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}