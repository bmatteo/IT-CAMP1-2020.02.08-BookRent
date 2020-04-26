package pl.camp.it.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.camp.it.config.AppConfigurationTest;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.session.SessionObject;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebMvcTest
public class UserControllerTest {

    @MockBean
    IRentDAO rentDAO;

    @MockBean
    SessionObject sessionObject;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void loginPageTest() throws Exception {
        Mockito.when(this.sessionObject.isLogged()).thenReturn(false);

        this.mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(model().attribute("rentList", hasSize(5)))
                .andExpect(model().attribute("rentList", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("userId", is(33))
                        )
                )));
    }
}
