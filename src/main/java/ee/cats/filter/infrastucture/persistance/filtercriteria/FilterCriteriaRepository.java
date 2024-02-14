package ee.cats.filter.infrastucture.persistance.filtercriteria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterCriteriaRepository extends JpaRepository<FilterCriteria, Long> {
}
