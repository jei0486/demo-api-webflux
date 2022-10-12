package com.demo.api.config;

import com.demo.api.domain.BoardRepository;
import com.demo.api.service.BoardHandler;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;


@Configuration
public class WebServerConfig {

    @Bean
    Scheduler jdbcScheduler(Environment env) {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(env.getRequiredProperty("jdbc.connection.pool.size", Integer.class)));
    }

    @Bean
    BoardHandler boardHandler(BoardRepository BoardRepository, Scheduler jdbcScheduler) {
        return new BoardHandler(BoardRepository, jdbcScheduler);
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(BoardHandler boardHandler) {

        return RouterFunctions.route()
                .POST("/board/insert", boardHandler::save)
                .build();
    }

    @Bean
    HttpHandler webHandler(RouterFunction<ServerResponse> routerFunction) {
        return RouterFunctions.toHttpHandler(routerFunction);
    }

    @Bean
    ReactiveWebServerFactory reactiveWebServerFactory(Environment env) {
        return new NettyReactiveWebServerFactory(env.getRequiredProperty("server.port", Integer.class));
    }
}
