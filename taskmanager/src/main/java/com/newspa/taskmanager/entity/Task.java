package com.newspa.taskmanager.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "tareas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name="mySeqGen", initialValue = 20, allocationSize = 2)
    @Column(name="id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name="title", nullable = false,  length = 50)
    private String title;

    @Column(name="description", nullable = false, length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name= "user_id", updatable = false)
    private UserEntity userEntity;
    /*
    @ElementCollection(targetClass = State.class)
    @JoinTable(name="estado_tareas", joinColumns= @JoinColumn(name="task_id"))
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<State> status = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name= "state_id", updatable = false)
    private State state;


    @JoinColumn(name= "created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updateAt")
    private LocalDateTime updateAt;

}