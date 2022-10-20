package com.demo.api.config;

import com.demo.api.domain.BoardRepository;
import com.demo.api.service.BoardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class WebServerConfig {

    @Bean
    BoardHandler boardHandler(BoardRepository boardRepository) {
        return new BoardHandler(boardRepository);
    }


    @Bean
    RouterFunction<ServerResponse> routerFunction(BoardHandler boardHandler) {
        return RouterFunctions.route()
                .GET("/board",accept(APPLICATION_JSON),boardHandler::list)
                .GET("/board/{boardId}",accept(APPLICATION_JSON),boardHandler::show)
                .POST("/board", boardHandler::save)
                .DELETE("/board/{boardId}",boardHandler::delete)
                .PUT("/board/{boardId}",accept(APPLICATION_JSON),boardHandler::update)
                .build();
    }


}
