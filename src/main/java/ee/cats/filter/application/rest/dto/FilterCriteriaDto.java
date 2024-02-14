package ee.cats.filter.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteriaDto {

    private String type;
    private String comparisonOperator;
    private String value;
}
