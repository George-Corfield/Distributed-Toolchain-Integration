package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")

public class User {

    @Id
    private ObjectId userId;
    private String username;
    private byte[] password;
    private String email;
    private int role;
    private byte[] salt;

    public User(){
        super();
    }

    public User(ObjectId userId, String username, byte[] password, String email, int role, byte[] salt){
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public byte[] getSalt(){
        return salt;
    }

    public void setSalt(byte[] salt){
        this.salt = salt;
    }



}

