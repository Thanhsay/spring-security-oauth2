package com.example.oath2sercurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @NotNull
    private String name;

    private String birthday;

    @ManyToMany
    @JoinTable(
            name = "user_position",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<Position> positions;

    @Column(name = "begin_work_date")
    private String beginWorkDate;

    @Column(name = "end_work_date")
    private String endWorkDate;

    @NotNull
    @JsonIgnore
    @Column(name = "lock_status")
    private boolean lock;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    @JsonIgnore
    private String passWord;

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private String email;

    private String phoneNumber;

    private String address;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @CreatedDate
    @Column(updatable = false, name = "created_date")
    private Instant createdDate = Instant.now();

    @CreatedBy
    @Column(updatable = false, name = "created_by")
    @JsonIgnore
    private String createdBy;
}
