package sample.entity;

import org.hibernate.annotations.GenericGenerator;
import sample.util.LocalDateAttributeConverter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "sample")
public class UsersEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String userPass;
    private Collection<MoviesEntity> moviesById;
    private Collection<UserRolesEntity> userRolesById;
    public UsersEntity() {

    }

    public UsersEntity(String firstName, String lastName, String userName, String userPass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPass = userPass;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pass")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPass != null ? !userPass.equals(that.userPass) : that.userPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersBySubmittedBy")
    public Collection<MoviesEntity> getMoviesById() {
        return moviesById;
    }

    public void setMoviesById(Collection<MoviesEntity> moviesById) {
        this.moviesById = moviesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserRolesEntity> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRolesEntity> userRolesById) {
        this.userRolesById = userRolesById;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
