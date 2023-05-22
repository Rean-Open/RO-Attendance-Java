package edu.aeu.msit.assigment.attendance.qr.model.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractPersistableEntity<ID> implements Serializable {

    private static final long serialVersionUID = 1940004612885378846L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @Version
    private Long version;
}
