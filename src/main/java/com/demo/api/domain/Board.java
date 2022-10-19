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
    private int hits;
    private String ins_id;
    private LocalDateTime ins_date;
    private String mod_id;
    private LocalDateTime mod_date;
}
