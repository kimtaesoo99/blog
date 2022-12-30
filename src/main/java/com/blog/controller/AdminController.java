package com.blog.controller;


import com.blog.config.auth.PrincipalDetail;
import com.blog.domain.Board;
import com.blog.domain.RoleType;
import com.blog.domain.User;
import com.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {
            return "/admin/adminMainPage";
        } else {
            return "redirect:/";
        }
    }


    // 전체 회원 관리
    @GetMapping("/admin/manage/member")
    public String manageMember(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {
            List<User> allUsers = adminService.findAllUser();
            model.addAttribute("allUsers", allUsers);
            return "/admin/manageMember";
        } else {
            return "redirect:/";
        }
    }


    // 회원 상세정보 수정 및 관리
    @GetMapping("/admin/manage/member/{id}")
    public String manageMemberDetail(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {
            User member = adminService.findUserById(id);
            List<Board> boards = adminService.findAllBoardByUser(member);

            model.addAttribute("user", member);
            model.addAttribute("boards", boards);
            return "/admin/manageMemberDetail";
        } else {
            return "redirect:/";
        }
    }





    // 방문 통계 관리
    @GetMapping("/admin/manage/visit")
    public String manageVisit(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {

        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {
            int totalVisitCount = adminService.getTotalViewCount();
            int totalUsersCount = adminService.getTotalUsersCount();
            model.addAttribute("totalVisitCount", totalVisitCount);
            model.addAttribute("totalUserCount", totalUsersCount);



            return "/admin/manageVisit";
        } else {
            return "redirect:/";
        }
    }

}
