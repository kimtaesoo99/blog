package com.blog.domain.api;


import com.blog.domain.ROLETYPE;
import com.blog.domain.User;
import com.blog.dto.ResponseDto;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    // Json 데이터를 받으려면 @RequestBody로 받아야함
    // 회원가입
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // 자바 오브젝트를 JSON으로 변환하여 전송 (JACKSON)
    }


}
