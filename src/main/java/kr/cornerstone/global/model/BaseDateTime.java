package kr.cornerstone.global.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDateTime {

    @CreatedDate
    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;

}
