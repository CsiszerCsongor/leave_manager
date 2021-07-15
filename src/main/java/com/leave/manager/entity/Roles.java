package com.leave.manager.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Roles {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @ManyToMany(mappedBy = "rolesList")
    private Set<Users> userList;
}
