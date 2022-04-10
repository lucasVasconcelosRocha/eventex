package br.com.lrvasconcelos.eventex.domain.entity;

import br.com.lrvasconcelos.eventex.api.dto.activity.ActivityDTO;
import br.com.lrvasconcelos.eventex.api.dto.event.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "start_hour")
    private LocalTime startHour;

    @NotNull
    @Column(name = "end_hour")
    private LocalTime endHour;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "vacancies")
    private Integer vacancies;

    @NotNull
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public ActivityDTO convertEntityToDto() {
        return new ModelMapper().map(this, ActivityDTO.class);
    }
}
