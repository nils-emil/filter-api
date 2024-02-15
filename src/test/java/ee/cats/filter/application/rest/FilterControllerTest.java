package ee.cats.filter.application.rest;

import ee.cats.filter.application.rest.dto.FilterCriteriaDto;
import ee.cats.filter.application.rest.dto.FilterCriteriaMapper;
import ee.cats.filter.application.rest.dto.FilterDto;
import ee.cats.filter.application.rest.dto.FilterMapper;
import ee.cats.filter.domain.FilterService;
import ee.cats.filter.infrastucture.persistance.filter.Filter;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterControllerTest {
    @Mock
    private FilterService filterService;

    @Mock
    private FilterMapper filterMapper;

    @Mock
    private FilterCriteriaMapper filterCriteriaMapper;

    private FilterController filterController;

    @BeforeEach
    public void setup() {
        filterController = new FilterController(filterService, filterMapper, filterCriteriaMapper);
    }

    @Test
    public void createFilter_callsRequiredServices() {
        FilterDto filterDto = new FilterDto();
        filterDto.setName("Test Filter");
        List<FilterCriteriaDto> criteria = new ArrayList<>();
        criteria.add(new FilterCriteriaDto());
        filterDto.setCriteria(criteria);
        Filter createdFilter = new Filter();
        createdFilter.setName("Test Filter");
        when(filterCriteriaMapper.toEntity(any())).thenReturn(new FilterCriteria());
        when(filterService.createFilter(any())).thenReturn(createdFilter);
        when(filterMapper.toDto(createdFilter)).thenReturn(filterDto);

        FilterDto result = filterController.createFilter(filterDto);

        assertEquals(filterDto, result);
        verify(filterCriteriaMapper, times(1)).toEntity(any());
        verify(filterService, times(1)).createFilter(any());
        verify(filterMapper, times(1)).toDto(createdFilter);
    }

    @Test
    public void getAllFilters_callsRequiredServices() {
        List<Filter> filters = new ArrayList<>();
        Filter filter1 = new Filter();
        filter1.setName("name1");
        Filter filter2 = new Filter();
        filter2.setName("name2");
        filters.add(filter1);
        filters.add(filter2);
        when(filterService.getAll()).thenReturn(filters);
        when(filterMapper.toDto(any())).thenAnswer(invocation -> {
            Filter filter = invocation.getArgument(0);
            FilterDto dto = new FilterDto();
            dto.setName(filter.getName());
            return dto;
        });

        List<FilterDto> result = filterController.getAllFilters();

        assertEquals(filters.size(), result.size());
        List<String> filterNames = filters.stream().map(Filter::getName).collect(Collectors.toList());
        List<String> dtoNames = result.stream().map(FilterDto::getName).collect(Collectors.toList());
        assertEquals(filterNames, dtoNames);
        verify(filterService, times(1)).getAll();
        verify(filterMapper, times(filters.size())).toDto(any());
    }
}