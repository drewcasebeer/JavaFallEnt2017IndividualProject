package sample.persistence;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import sample.entity.UserRolesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drew Casebeer
 */
public class UserRolesDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all user roles
     *
     * @return All user roles
     */
    public List<UserRolesEntity> getAllUserRoles() {
        List<UserRolesEntity> userRoles = new ArrayList<UserRolesEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            userRoles = session.createCriteria(UserRolesEntity.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all user roles", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userRoles;
    }

    /** Get a single user role for the given id
     *
     * @param id user role's id
     * @return UserRolesEntity
     */
    public UserRolesEntity getUserRole(int id) {
        UserRolesEntity userRole = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            userRole = (UserRolesEntity) session.get(UserRolesEntity.class, id);
        } catch (HibernateException he) {
            log.error("Error getting user role with id: " + id, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userRole;
    }

    /** Retrieve user roles by user role
     *
     * @param userRole user roles's last name which is the search criteria
     * @return user roles
     */
    public List<UserRolesEntity> getUserRolesByName(String userRole) {
        List<UserRolesEntity> userRoles = new ArrayList<UserRolesEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(UserRolesEntity.class);
            criteria.add(Restrictions.eq("userRole", userRole));
            userRoles = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting all user roles with user role: " + userRole, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userRoles;
    }

    /** save new user roles
     * @param userRole user role to insert
     * @return id of the inserted user role
     */

    public int insert(UserRolesEntity userRole) {
        int id = 0;
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            id = (int) session.save(userRole);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user role: " + userRole, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /** save  user role
     * @param userRole user role to update
     */

    public void update(UserRolesEntity userRole) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(userRole);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user role: " + userRole, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}