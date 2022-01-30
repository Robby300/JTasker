package ru.jtasker.domain;

public class User {
    private long id;
    private String userName;
    private String password;
    private String email;

    public User() {
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
    }

    public static class Builder {

        private long id;
        private String userName;
        private String password;
        private String email;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName=" + userName +
                ", password=" + password +
                ", email=" + email +
                '}';
    }
}


