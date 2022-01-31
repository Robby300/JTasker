package ru.jtasker.domain;

public class User {
    private long id;
    private String userName;
    private String password;

    public User() {
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.password = builder.password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName()) && getPassword().equals(user.getPassword());
    }

    @Override
    public String toString() {
        return "Пользователь " +
                "id= " + id +
                ", Имя: " + userName
                //", password=" + password +
                //", email=" + email +
                ;
    }
}


