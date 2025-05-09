/*package com.newspa.taskmanager.entity;


import lombok.Data;


public enum State {
    OPEN, PROGRESS, COMPLETED
}*/
package com.newspa.taskmanager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="estado_tareas")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @Column(name="id", updatable = false)
    private Long id;

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<Task> task;

}

