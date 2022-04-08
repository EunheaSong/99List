package com.hanghea.list99.domain;


import com.hanghea.list99.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Setter
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @OneToMany(mappedBy = "user")
    private List<Plan> plans;

    public User(UserDto userDto){
        this.userId = userDto.getUserId();
        this.userPw = userDto.getUserPw();
    }
}
