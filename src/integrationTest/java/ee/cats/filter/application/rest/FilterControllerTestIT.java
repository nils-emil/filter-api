package ee.cats.filter.application.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.cats.filter.application.rest.dto.FilterCriteriaDto;
import ee.cats.filter.application.rest.dto.FilterDto;
import ee.cats.filter.infrastucture.persistance.filter.FilterRepository;
import ee.cats.filter.infrastucture.persistance.filtercriteria.FilterCriteriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FilterControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private FilterCriteriaRepository filterCriteriaRepository;


    @BeforeEach
    public void cleanup() {
        filterRepository.deleteAll();
        filterCriteriaRepository.deleteAll();
    }

    @Test
    public void createFilter_createsFilter_ifAllDataValid() throws Exception {
        FilterDto filterDto = FilterDto
                .builder()
                .name("Test Filter")
                .criteria(List.of(FilterCriteriaDto
                        .builder()
                        .type("Test Criteria")
                        .comparisonOperator("comparisonOperator")
                        .value("value")
                        .build()))
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filterDto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        FilterDto responseDto = objectMapper.readValue(responseBody, FilterDto.class);

        assertThat(responseDto.getName()).isEqualTo("Test Filter");
        assertThat(responseDto.getCreateTime()).isNotNull();
        assertThat(responseDto.getCriteria()).hasSize(1);
        assertThat(responseDto.getCriteria().get(0).getComparisonOperator()).isEqualTo("comparisonOperator");
        assertThat(responseDto.getCriteria().get(0).getValue()).isEqualTo("value");
        assertThat(responseDto.getCriteria().get(0).getType()).isEqualTo("Test Criteria");
    }


    @Test
    public void getFilters_receivesAllFilters() throws Exception {
        FilterDto filterDto1 = FilterDto
                .builder()
                .name("TestFilter")
                .criteria(List.of(FilterCriteriaDto
                        .builder()
                        .type("TestCriteria1")
                        .comparisonOperator("comparisonOperator1")
                        .value("value1")
                        .build()))
                .build();
        FilterDto filterDto2 = FilterDto
                .builder()
                .name("TestFilter1")
                .criteria(List.of(
                        FilterCriteriaDto
                                .builder()
                                .type("TestCriteria2")
                                .comparisonOperator("comparisonOperator2")
                                .value("value2")
                                .build(),
                        FilterCriteriaDto
                                .builder()
                                .type("TestCriteria3")
                                .comparisonOperator("comparisonOperator3")
                                .value("value3")
                                .build()))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filterDto1)))
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filterDto2)))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/filters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<FilterDto> responseFilters = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertThat(responseFilters).hasSize(2);
        assertThat(responseFilters.get(0).getName()).isEqualTo("TestFilter");
        assertThat(responseFilters.get(0).getCreateTime()).isNotNull();
        assertThat(responseFilters.get(0).getCriteria()).hasSize(1);
        assertThat(responseFilters.get(0).getCriteria().get(0).getComparisonOperator()).isEqualTo("comparisonOperator1");
        assertThat(responseFilters.get(0).getCriteria().get(0).getValue()).isEqualTo("value1");
        assertThat(responseFilters.get(0).getCriteria().get(0).getType()).isEqualTo("TestCriteria1");
        assertThat(responseFilters.get(1).getName()).isEqualTo("TestFilter1");
        assertThat(responseFilters.get(1).getCreateTime()).isNotNull();
        assertThat(responseFilters.get(1).getCriteria()).hasSize(2);
        assertThat(responseFilters.get(1).getCriteria().get(0).getComparisonOperator()).isEqualTo("comparisonOperator2");
        assertThat(responseFilters.get(1).getCriteria().get(0).getValue()).isEqualTo("value2");
        assertThat(responseFilters.get(1).getCriteria().get(0).getType()).isEqualTo("TestCriteria2");
        assertThat(responseFilters.get(1).getCriteria().get(1).getComparisonOperator()).isEqualTo("comparisonOperator3");
        assertThat(responseFilters.get(1).getCriteria().get(1).getValue()).isEqualTo("value3");
        assertThat(responseFilters.get(1).getCriteria().get(1).getType()).isEqualTo("TestCriteria3");

    }

}