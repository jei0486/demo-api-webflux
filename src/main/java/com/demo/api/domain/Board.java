package com.demo.api.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
    private Long seq;
    private String subject;
    private String content;
    private Integer hits;
    private String createdId;
    private LocalDateTime createdDate;
    private String modifiedId;
    private LocalDateTime modifiedDate;
}
