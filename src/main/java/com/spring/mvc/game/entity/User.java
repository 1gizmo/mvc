package com.spring.mvc.game.entity;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String userName;

    private String userAge;

    private String userId;

    private String userPw;

    private String userAddr;

    public User(String userName, String userAge, String userAddr) {
        this.userName = userName;
        this.userAge = userAge;
        this.userAddr = userAddr;
    }

    public User(String userName, String userAge, String userId, String userPw, String userAddr) {
        this.userName = userName;
        this.userAge = userAge;
        this.userId = userId;
        this.userPw = userPw;
        this.userAddr = userAddr;
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.userAge =  user.getUserAge();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userAddr = user.getUserAddr();
    }
}
