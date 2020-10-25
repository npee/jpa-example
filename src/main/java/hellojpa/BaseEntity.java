package hellojpa;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    private String createdBy;
    private String modifiedBy;

    @CreationTimestamp // Spring Data JPA에서는 @CreatedDate로 대체할 수 있다.
    private LocalDateTime createdTime;

    @UpdateTimestamp // Spring Data JPA에서는 @LastModifiedDate로 대체할 수 있다.
    private LocalDateTime lastModifiedTime;
}
