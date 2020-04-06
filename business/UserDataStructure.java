package business;

public class UserDataStructure {
    private Long idUser;
    private String username;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private String passCheck;
    
    public UserDataStructure(String username, String password){
        this.username = username;
        this.password = password;
        this.passCheck = password;
    }
    
    public UserDataStructure(String username, String password, String emailAddress, String phoneNumber, String passCheck){
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.passCheck = passCheck;
    }
    
    public Long getIdUser(){
        return idUser;
    }
    
    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassCheck(){
        return passCheck;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
}