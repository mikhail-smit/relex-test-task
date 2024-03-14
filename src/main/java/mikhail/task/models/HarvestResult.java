package mikhail.task.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "harvest_results")
@Data
@NoArgsConstructor
public class HarvestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id")
    private Product product;

    @Column(name = "count")
    private int count;

    @Column(name = "at_moment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atMoment;
}