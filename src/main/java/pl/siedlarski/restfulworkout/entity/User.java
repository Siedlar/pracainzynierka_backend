package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
@UniqueConstraint(columnNames = "username"),
@UniqueConstraint(columnNames = "email")
		})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private PasswordResetToken resetToken;

    public PasswordResetToken getResetToken() {
        return resetToken;
    }
    @OneToOne
    @JoinColumn(name="userinfo_id")
        private UserInfo userInfo;
    @OneToOne
    @JoinColumn(name="id")
    private ImageModel imageModel;
    @OneToMany(mappedBy="user")
    private List<Wymiar> wymiar;
    @OneToMany(mappedBy="user")
    private List<Rekord> rekord;
    @OneToMany(mappedBy="user")
    private List<HistoriaTreningu> historiaTreningu;



    public void setHistoriaTreningu(List<HistoriaTreningu> historiaTreningu) {
        this.historiaTreningu = historiaTreningu;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }


    public void setWymiar(List<Wymiar> wymiar) {
        this.wymiar = wymiar;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<Wymiar> getWymiar() {
        return wymiar;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setResetToken(PasswordResetToken resetToken) {
        this.resetToken = resetToken;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(@NotBlank String login, @NotBlank String email, @NotBlank String password) {
        this.username = login;
        this.email = email;
        this.password = password;
    }
}
