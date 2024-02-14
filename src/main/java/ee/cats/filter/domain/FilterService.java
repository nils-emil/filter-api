package ee.cats.filter.domain;

import ee.cats.filter.application.rest.command.CreateFilterCommand;
import ee.cats.filter.infrastucture.persistance.filter.Filter;
import ee.cats.filter.infrastucture.persistance.filter.FilterRepository;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;
    private final FilterCriteriaRepository filterCriteriaRepository;

    public Filter createFilter(CreateFilterCommand command) {
        Filter filter = new Filter();
        filter.setName(command.getName());
        filter.setCreateTime(Instant.now());
        Filter savedFilter = filterRepository.save(filter);
        List<FilterCriteria> criteria = getCriteriaWithCorrectFilterAttached(command, savedFilter);
        List<FilterCriteria> savedCriteria = filterCriteriaRepository.saveAll(criteria);
        filter.setCriteria(savedCriteria);
        return filter;
    }

    public List<Filter> getAll() {
        return filterRepository.findAll();
    }

    private List<FilterCriteria> getCriteriaWithCorrectFilterAttached(CreateFilterCommand command, Filter filter) {
        return command.getCriteria().stream()
                .map(criteria -> FilterCriteria
                        .builder()
                        .filter(filter)
                        .value(criteria.getValue())
                        .comparisonOperator(criteria.getComparisonOperator())
                        .criteriaType(criteria.getCriteriaType())
                        .build())
                .collect(Collectors.toList());
    }

}
