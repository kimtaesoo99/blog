package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> 오브젝트를 매핑해줌
@Entity //User클래스가 MYSQL 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라감
    private Long id;

    @Column(nullable = false,length = 30)
    private String userName;

    @Column(nullable = false,length = 100) // -> 해쉬 (비밀번호 암호화)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private  ROLETYPE role; //Enum을 쓰는게 좋음.

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;

}
