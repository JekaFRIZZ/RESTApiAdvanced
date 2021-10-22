package com.epam.esm.entity;

import com.epam.esm.audit.AuditType;
import com.epam.esm.util.AuditUtils;
import lombok.*;
import org.apache.log4j.Logger;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User extends RepresentationModel<User> implements Identifiable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;

    @PostPersist
    public void onPostPersist() {
        AuditUtils.audit(AuditType.INSERT, this);
    }

    @PostUpdate
    public void onPostUpdate() {
        AuditUtils.audit(AuditType.UPDATE, this);
    }

    @PostRemove
    public void onPostRemove() {
        AuditUtils.audit(AuditType.REMOVE, this);
    }
}
