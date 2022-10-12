package com.demo.api.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="board")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String subject;
    private String content;
    private int hits;
    private Character del_yn;
    private String ins_id;

    @CreatedDate
    private LocalDateTime ins_date;

    private String mod_id;

    @LastModifiedDate
    private LocalDateTime mod_date;

}
