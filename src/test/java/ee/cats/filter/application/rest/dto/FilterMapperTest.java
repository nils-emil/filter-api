package ee.cats.filter.application.rest.dto;

import ee.cats.filter.infrastucture.persistance.filter.Filter;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterMapperTest {

    @Mock
    private FilterCriteriaMapper filterCriteriaMapper;

    @InjectMocks
    private FilterMapper filterMapper;

    @Test
    public void toDto_hasCorrectMapping() {
        Filter filter = new Filter();
        filter.setName("Test Filter");
        filter.setCreateTime(Instant.now());
        List<FilterCriteria> criteria = new ArrayList<>();
        criteria.add(new FilterCriteria());
        filter.setCriteria(criteria);
        FilterCriteriaDto criteriaDto = new FilterCriteriaDto();
        when(filterCriteriaMapper.toDto(any())).thenReturn(criteriaDto);

        FilterDto result = filterMapper.toDto(filter);

        assertThat(filter.getName()).isEqualTo(result.getName());
        assertThat(filter.getCreateTime()).isEqualTo(result.getCreateTime());
        assertThat(criteria.size()).isEqualTo(result.getCriteria().size());
        verify(filterCriteriaMapper, times(criteria.size())).toDto(any());
    }

}