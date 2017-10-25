package sample.persistence;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import sample.entity.MoviesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drew Casebeer
 */
public class MoviesDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all movies
     *
     * @return All movies
     */
    public List<MoviesEntity> getAllMovies() {
        List<MoviesEntity> movies = new ArrayList<MoviesEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            movies = session.createCriteria(MoviesEntity.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all movies", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movies;
    }

    /** Get a single movie for the given id
     *
     * @param id movie's id
     * @return Movie
     */
    public MoviesEntity getMovie(int id) {
        MoviesEntity movie = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            movie = (MoviesEntity) session.get(MoviesEntity.class, id);
        } catch (HibernateException he) {
            log.error("Error getting movie with id: " + id, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    /** Retrieve movies by name
     *
     * @param name Movie's last name which is the search criteria
     * @return Movie
     */
    public List<MoviesEntity> getMoviesByName(String name) {
        List<MoviesEntity> movies = new ArrayList<MoviesEntity>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(MoviesEntity.class);
            criteria.add(Restrictions.eq("name", name));
            movies = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting all movies with last name: " + name, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movies;
    }

    /** save new movie
     * @param movie movie to insert
     * @return id of the inserted movie
     */

    public int insert(MoviesEntity movie) {
        int id = 0;
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            id = (int) session.save(movie);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of movie: " + movie, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /** save  movie
     * @param movie movie to update
     */

    public void update(MoviesEntity movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(movie);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of movie: " + movie, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}