package sample.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Movies", schema = "sample")
public class MoviesEntity {
    private int id;
    private String name;
    private String year;
    private int votes;
    private Integer submittedBy;
    private UsersEntity usersBySubmittedBy;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "votes")
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesEntity that = (MoviesEntity) o;

        if (id != that.id) return false;
        if (votes != that.votes) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (submittedBy != null ? !submittedBy.equals(that.submittedBy) : that.submittedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + votes;
        result = 31 * result + (submittedBy != null ? submittedBy.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "submitted_by", referencedColumnName = "id")
    public UsersEntity getUsersBySubmittedBy() {
        return usersBySubmittedBy;
    }

    public void setUsersBySubmittedBy(UsersEntity usersBySubmittedBy) {
        this.usersBySubmittedBy = usersBySubmittedBy;
    }
}
