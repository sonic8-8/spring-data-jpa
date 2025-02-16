package study.springdatajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    // JPA 식별자 생성 전략이 GeneratedValue 가 아닐 경우
    // save() 호출 시점에 값이 들어있으므로 새로운 엔티티인지 구별할 방법이 필요함
    // 이를 위해 생성 날짜가 없는지 확인하는 것으로 대체하고 이를 실무에서도 사용함
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id, LocalDateTime createdDate) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
