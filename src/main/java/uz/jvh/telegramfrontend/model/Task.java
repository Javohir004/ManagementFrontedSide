package uz.jvh.telegramfrontend.model;
import jakarta.persistence.*;
import lombok.*;
import uz.jvh.telegramfrontend.enumerators.StatusEnum;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "tasks")
public class Task extends BaseEntity {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    private FeatureEntity feature;

    @OneToOne
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "sprint_id") // This is the foreign key in the 'tasks' table
    private Sprint sprint;
}
