package ee.cats.filter.application.rest.command;

import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteria;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateFilterCommand {

    private String name;
    private List<FilterCriteria> criteria;

}
