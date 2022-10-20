package com.demo.api.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 테이블로 매핑하지 않고, 자식 Entity에게 매핑정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class) // JPA에게 해당 Entity는 Auditing 기능을 사용한다는 것을 알리는 어노테이션
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간이 자동 저장
    @CreatedDate
    @Column(updatable = false,name="ins_date")
    private LocalDateTime createdDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    @Column(name="mod_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

