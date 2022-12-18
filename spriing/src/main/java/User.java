public class User {
    private Long id;
    private String username;

    private User user;

    public User() {
    }

    public User(Long id, String username, User user) {
        this.id = id;
        this.username = username;
        this.user = user;
        System.out.println("通过改造函数注入属性");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        System.out.println("setId");
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("setUsername");
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }

}
