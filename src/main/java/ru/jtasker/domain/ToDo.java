package ru.jtasker.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String name;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime deadline;
    private boolean isDone;
    private Long parentToDoId;
}
