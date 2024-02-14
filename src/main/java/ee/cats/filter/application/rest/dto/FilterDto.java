package ee.cats.filter.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

    private String name;
    private List<FilterCriteriaDto> criteria;
    private Instant createTime;
}
