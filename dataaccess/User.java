package dataaccess;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "User")
@Table(name ="userdata")
public class User {
    @Id
    @Column(name = "idUser")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private Long  idUser;
    
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "idUser")
    private Set groceryItems;
        
    public Long getIdUser(){
        return idUser;
    }
    
    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }

    @Column(name = "Username")
    private String username;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    @Column(name = "Password")
    private String password;
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    @Column(name="EmailAddress")
    private String emailAddress;
    
    public String getEmailAddress(){
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public Set getGroceryItem(){
        return groceryItems;
    }
   
    public void setGroceryItem(Set groceryItems ){
        this.groceryItems = groceryItems;
    }
}


