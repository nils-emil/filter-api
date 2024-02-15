package ee.cats.filter.application.rest;

import ee.cats.filter.application.rest.command.CreateFilterCommand;
import ee.cats.filter.application.rest.dto.FilterCriteriaMapper;
import ee.cats.filter.application.rest.dto.FilterDto;
import ee.cats.filter.application.rest.dto.FilterMapper;
import ee.cats.filter.domain.FilterService;
import ee.cats.filter.infrastucture.persistance.filter.Filter;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("api")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;
    private final FilterMapper filterMapper;
    private final FilterCriteriaMapper filterCriteriaMapper;

    @CrossOrigin
    @PostMapping("/filter")
    public FilterDto createFilter(@RequestBody FilterDto filter) {
        List<FilterCriteria> criteria = filter.getCriteria().stream().map(filterCriteriaMapper::toEntity).collect(Collectors.toList());
        CreateFilterCommand command = CreateFilterCommand.builder().name(filter.getName()).criteria(criteria).build();
        Filter createdFilter = filterService.createFilter(command);
        return filterMapper.toDto(createdFilter);
    }

    @CrossOrigin
    @GetMapping("/filters")
    public List<FilterDto> getAllFilters() {
        return filterService.getAll().stream().map(filterMapper::toDto).collect(Collectors.toList());
    }

}
