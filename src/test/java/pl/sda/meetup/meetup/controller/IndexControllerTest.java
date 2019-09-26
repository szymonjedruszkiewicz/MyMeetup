package pl.sda.meetup.meetup.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.mapper.manual.ManualEventMapper;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.repository.UserRepository;
import pl.sda.meetup.meetup.service.EventService;
import pl.sda.meetup.meetup.service.EventServiceImpl;
import pl.sda.meetup.meetup.service.UserContextService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    private IndexController indexController;

    @Mock
    EventService eventService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(eventService);
    }


    @Test
    void shouldFillIndexWithEvents() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(indexController).build();

        EventDto first = EventDto.builder()
                .id(1L)
                .build();
        EventDto second = EventDto.builder()
                .id(2L)
                .build();

        List<EventDto> testList = new ArrayList<>();
        testList.add(first);
        testList.add(second);

        when(eventService.getEventList()).thenReturn(testList);
        String viewName = indexController.showIndexPage(model);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("events", testList));


        Assertions.assertEquals("index", viewName);
        
    }
}