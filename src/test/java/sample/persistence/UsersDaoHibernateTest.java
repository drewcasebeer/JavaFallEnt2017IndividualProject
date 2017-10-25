package sample.persistence;

import javafx.util.converter.LocalDateStringConverter;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sample.entity.UsersEntity;

import java.util.List;

import static org.junit.Assert.*;


public class UsersDaoHibernateTest {
/**
 * Created on 9/12/16.
 *
 * @author pwaite
 */


    UsersDao dao;
    int listOfUsersInitialSize;

    //@BeforeClass
    //// while you might be tempted to recreate the DB here, Hibernate offers this as
    // a config option. Check out hibernate.hbm2ddl.auto=create-drop

    @Before
    public void setup() {
        dao = new UsersDao();
        listOfUsersInitialSize = dao.getAllUsers().size();
    }

    /** Verify getAllUsers() returns a list with a size > **/
    @Test
    public void getAllUsersTest() throws Exception {
        List<UsersEntity> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    /** Verify getUser(id) returns the correct user.**/
    @Test
    public void getUserTest() throws Exception {
        UsersEntity user = dao.getUser(1);
        assertNotNull(user);
        // The assertion below could be more thorough
        // by comparing the returned user.toString to the expected user.toString
        assertEquals("Coyne", user.getLastName());
    }

    /** Verify getUsersByLastName returns the correct user/s.**/
    @Test
    public void getUsersByLastNameTest() throws Exception {
        List<UsersEntity> users = dao.getUsersByLastName("Coyne");
        assertEquals("Incorrect size of results", 1, users.size());
        // This could be more thorough by testing for the following conditions
        // expect 0 users
        // expect 1 user
        // expect multiple users

    }

    /** Verifies adding a new user **/
    @Test
    public void insertUserTest() throws Exception {
        LocalDateStringConverter dateConverter = new LocalDateStringConverter();
        // for more info on default formats for the converter
        // see https://docs.oracle.com/javase/tutorial/i18n/format/dateFormat.html
        UsersEntity user = new UsersEntity("Pete", "Wentzer", "test", "test");
        int idNewUser = dao.insert(user);
        System.out.println(idNewUser);
        System.out.println(dao.getUser(idNewUser));
        assertEquals("Incorrect size of results", listOfUsersInitialSize + 1, dao.getAllUsers().size());
        assertEquals("User not saved correctly", user.toString(), dao.getUser(idNewUser).toString());
    }

    /** Verifies updateUser updates an existing user **/
    @Test
    public void updateUserTest() throws Exception {
        listOfUsersInitialSize = dao.getAllUsers().size();
        int idOfToBeUpdatedUser = 2;
        UsersEntity user = dao.getUser(idOfToBeUpdatedUser);
        user.setLastName("Smith");
        dao.update(user);
        // verify a user was not inserted
        assertEquals("Incorrect size of results", listOfUsersInitialSize, dao.getAllUsers().size());
        // verify the update worked
        assertEquals("User not saved correctly", user.toString(), dao.getUser(idOfToBeUpdatedUser).toString());
    }
}