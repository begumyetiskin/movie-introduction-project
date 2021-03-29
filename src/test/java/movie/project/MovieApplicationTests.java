package movie.project;

import movie.project.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListCasts() throws Exception {
        mockMvc.perform(get("/cast/listCast")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].firstName").exists())
                .andExpect(jsonPath("$[*].lastName").exists());
    }

    @Test
    public void testCreateMovie() throws Exception {
        Movie newMovie = new Movie();
        newMovie.setName("Dr. Strange");
        newMovie.setReleaseYear(2016L);
        newMovie.setCategory("Fantastik");
        newMovie.setDescription("Marvel Yapımı");
        newMovie.setImageUrl("4.jpg");
        newMovie.setLanguage("Türkçe Dublaj");

        mockMvc.perform(post("/movie/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newMovie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.releaseYear").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.imageUrl").exists())
                .andExpect(jsonPath("$.language").exists());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
