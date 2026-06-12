package lv.venta.model;



import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Table(name = "ReviewTable")
@Entity
public class Review {
 
    @Column(name = "idREV")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idREV;
 
   
    //private User user;

    private Property property;
 
    @Min(1)
    @Max(5)
    @Column(name = "RatingScore")
    private int ratingScore;
 
    @NotNull
    @NotEmpty
    @Column(name = "Comment")
    private String comment;
 
    public int getIdREV() {
        return idREV;
    }
 
    //public User getUser() {
     //   return user;
   // }
 
    //public void setUser(User user) {
    //    this.user = user;
    //}
 
    public Property getProperty() {
        return property;
    }
 
    public void setProperty(Property property) {
        this.property = property;
    }
 
    public int getRatingScore() {
        return ratingScore;
    }
 
    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }
 
    public String getComment() {
        return comment;
    }
 
    public void setComment(String comment) {
        this.comment = comment;
    }
 
    public Review() {}
 
   /* public Review(User user, Property property, int ratingScore, String comment) {
        setUser(user);
        setProperty(property);
        setRatingScore(ratingScore);
        setComment(comment);
    }
 
    @Override
    public String toString() {
        return idREV + " User: [" + user + "] Property: [" + property + "] Rating: " + ratingScore + " Comment: " + comment;
    }*/
} 