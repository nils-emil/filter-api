package ee.cats.filter.application.rest.dto;

import ee.cats.filter.infrastucture.persistance.filter.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterMapper {

    private final FilterCriteriaMapper filterCriteriaMapper;

    public FilterDto toDto(Filter filter) {
        List<FilterCriteriaDto> criteria = filter.getCriteria()
                .stream()
                .map(filterCriteriaMapper::toDto)
                .collect(Collectors.toList());
        return FilterDto
                .builder()
                .name(filter.getName())
                .criteria(criteria)
                .createTime(filter.getCreateTime())
                .name(filter.getName())
                .build();
    }

}
