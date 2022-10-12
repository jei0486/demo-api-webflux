package com.demo.api.service;

import com.demo.api.domain.Board;
import com.demo.api.domain.BoardEntity;
import com.demo.api.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RequiredArgsConstructor
@Slf4j
public class BoardHandler {

    final BoardRepository boardRepository;

    final Scheduler scheduler;

    /*
        insert
     */
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Board> boardMono = request.bodyToMono(Board.class);

        return boardMono.flatMap(board
                        -> Mono.fromCallable(()
                        -> boardRepository.save(BoardEntity.builder()
                        .ins_id(board.getIns_id())
                        .subject(board.getSubject())
                        .content(board.getContent())
                        .del_yn(board.getDel_yn())
                        .build())))
                .publishOn(scheduler)
                .flatMap(board -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(board));
    }
}
