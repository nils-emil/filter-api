package ee.cats.filter.application.rest.dto;

import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilterCriteriaMapperTest {
    private FilterCriteriaMapper filterCriteriaMapper;

    @BeforeEach
    public void setup() {
        filterCriteriaMapper = new FilterCriteriaMapper();
    }

    @Test
    public void toEntity_hasCorrectMapping() {
        FilterCriteriaDto dto = new FilterCriteriaDto();
        dto.setType("Type");
        dto.setComparisonOperator("Operator");
        dto.setValue("Value");

        FilterCriteria result = filterCriteriaMapper.toEntity(dto);

        assertThat(result.getCriteriaType()).isEqualTo(dto.getType());
        assertThat(result.getComparisonOperator()).isEqualTo(dto.getComparisonOperator());
        assertThat(result.getValue()).isEqualTo(dto.getValue());
    }

    @Test
    public void toDto_hasCorrectMapping() {
        FilterCriteria entity = FilterCriteria.builder()
                .criteriaType("Type")
                .comparisonOperator("Operator")
                .value("Value")
                .build();

        FilterCriteriaDto result = filterCriteriaMapper.toDto(entity);

        assertThat(result.getType()).isEqualTo(entity.getCriteriaType());
        assertThat(result.getComparisonOperator()).isEqualTo(entity.getComparisonOperator());
        assertThat(result.getValue()).isEqualTo(entity.getValue());
    }

}