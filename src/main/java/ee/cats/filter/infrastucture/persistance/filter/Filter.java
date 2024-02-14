package ee.cats.filter.infrastucture.persistance.filter;

import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "filters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filter_name")
    private String name;

    @OneToMany(mappedBy = "filter")
    private List<FilterCriteria> criteria;

    @Column(name = "create_time")
    private Instant createTime;

}