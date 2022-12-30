package com.blog.repository;

import com.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //SELECT * FROM user WHERE username =?1 AND password =?2;
    //User findByUserNameAndPassword(String username,String password);
}
