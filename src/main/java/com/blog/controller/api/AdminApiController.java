package com.blog.controller.api;

import com.blog.config.auth.PrincipalDetail;
import com.blog.domain.RoleType;
import com.blog.domain.User;
import com.blog.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final AdminService adminService;


    // 관리자페이지에서 멤버 등급 바꾸기
    @PutMapping("/admin/manage/member/edit/{id}")
    public ResponseEntity<?> changeRole(@RequestBody User roleUser, @PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {
            System.out.println("컨트롤러 role = " + roleUser);
            adminService.changeRole(id, roleUser);

            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(1, HttpStatus.FORBIDDEN);
        }

    }
}
