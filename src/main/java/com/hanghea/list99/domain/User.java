package com.hanghea.list99.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@Setter
@Entity
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Plan> plans;

    public User (String userId, String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }

}
