package ee.cats.filter.application.rest.dto;

import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import org.springframework.stereotype.Service;

@Service
public class FilterCriteriaMapper {

    public FilterCriteria toEntity(FilterCriteriaDto filterCriteriaDto) {
        return FilterCriteria.builder()
                .criteriaType(filterCriteriaDto.getType())
                .comparisonOperator(filterCriteriaDto.getComparisonOperator())
                .value(filterCriteriaDto.getValue())
                .build();
    }

    public FilterCriteriaDto toDto(FilterCriteria filterCriteria) {
        return FilterCriteriaDto.builder()
                .type(filterCriteria.getCriteriaType())
                .comparisonOperator(filterCriteria.getComparisonOperator())
                .value(filterCriteria.getValue())
                .build();
    }


}
