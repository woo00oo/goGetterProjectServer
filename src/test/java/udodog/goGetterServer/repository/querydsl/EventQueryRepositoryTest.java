package udodog.goGetterServer.repository.querydsl;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.config.TestConfig;
import udodog.goGetterServer.model.dto.response.event.ProgressEventsResponseDto;


@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Slf4j
public class EventQueryRepositoryTest {

    @Autowired
    private EventQueryRepository eventQueryRepository;

    @Test
    public void 진행중인_이벤트_전체조회(){
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("startDate").descending());

        Page<ProgressEventsResponseDto> result = eventQueryRepository.progressEventFindAll(pageRequest);

        result.stream()
                .forEach(value -> log.info("title = {}, startDate = {}. endDate = {} ", value.getTitle(), value.getStartDate(), value.getEndDate()));

        Assertions.assertThat(result.getTotalElements()).isEqualTo(12);
    }

}