package edu.aeu.msit.assigment.attendance.qr.model.base;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import edu.aeu.msit.assigment.attendance.qr.model.Username;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity<ID> extends AbstractPersistableEntity<ID> implements Serializable {
    
    private static final long serialVersionUID = -2681258448691186044L;

	@CreatedDate
    LocalDateTime createdDate;
    
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
    
    @CreatedBy
    //@ManyToOne
    //@JoinColumn(name = "created_by")
    @AttributeOverride(name = "username", column = @Column(name = "created_by"))
    @Embedded
    Username createdBy;
    
    @LastModifiedBy
    //@ManyToOne
    //@JoinColumn(name = "last_modified_by")
    @AttributeOverride(name = "username", column = @Column(name = "last_modified_by"))
    @Embedded
    Username lastModifiedBy;
}
