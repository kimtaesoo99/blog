package com.blog.repository;

import com.blog.domain.Board;
import com.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByUserOrderByIdDesc(User user);
    Page<Board> findByCategory(Pageable pageable, String category);
}
