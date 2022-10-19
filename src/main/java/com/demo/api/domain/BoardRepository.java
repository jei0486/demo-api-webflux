package com.demo.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    // 게시물 수정
    //public Board updateBoard(Board board);
}
