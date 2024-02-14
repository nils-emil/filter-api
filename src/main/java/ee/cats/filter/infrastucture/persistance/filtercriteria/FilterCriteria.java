package ee.cats.filter.infrastucture.persistance.filtercriteria;

import ee.cats.filter.infrastucture.persistance.filter.Filter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "filter_criteria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    private Filter filter;

    @Column(name = "criteria_type")
    private String criteriaType;

    @Column(name = "comparison_operator")
    private String comparisonOperator;

    @Column(name = "comparison_value")
    private String value;


}