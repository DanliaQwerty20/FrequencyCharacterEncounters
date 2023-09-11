package io.proj3ct.testjob;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showFrequencyFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to Job...")));

    }

    @Test
    public void calculateFrequencyTest() throws Exception {
        String inputString = "aaaaabcccc";

        mockMvc.perform(MockMvcRequestBuilders.post("/frequency")
                        .param("inputString", inputString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("frequency-result"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("frequencyMap"))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('a', 5)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('c', 4)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('b', 1)));
    }

    @Test
    public void calculateFrequencyTestWithSpecialCharacters() throws Exception {
        String inputString = "!@#$%^&*()_+";

        mockMvc.perform(MockMvcRequestBuilders.post("/frequency")
                        .param("inputString", inputString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("frequency-result"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("frequencyMap"))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('!', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('@', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('#', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('$', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('%', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('^', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('&', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('*', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('(', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry(')', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('_', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap",
                        Matchers.hasEntry('+', 1)));
    }


    @Test
    public void calculateFrequencyInputWithDigitsTest() throws Exception {
        String inputString = "abcd1234efgh";

        mockMvc.perform(MockMvcRequestBuilders.post("/frequency")
                        .param("inputString", inputString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("frequency-result"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("frequencyMap"))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('a', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('b', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('c', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('d', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('e', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('f', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('g', 1)))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.hasEntry('h', 1)));
    }

    @Test
    public void calculateFrequencyEmptyInputTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/frequency")
                        .param("inputString", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("frequency-result"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("frequencyMap"))
                .andExpect(MockMvcResultMatchers.model().attribute("frequencyMap", Matchers.anEmptyMap()));
    }
}