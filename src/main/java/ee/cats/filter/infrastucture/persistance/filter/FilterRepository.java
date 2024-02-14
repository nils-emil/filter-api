package ee.cats.filter.infrastucture.persistance.filter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
