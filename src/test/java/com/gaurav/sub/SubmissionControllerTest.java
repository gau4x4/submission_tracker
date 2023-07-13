package com.gaurav.sub;

import com.gaurav.sub.submission.SubmissionController;
import com.gaurav.sub.submission.SubmissionDTO;
import com.gaurav.sub.submission.SubmissionService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SubmissionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubmissionService submissionService;

    @Test
    public void testAddNewSubmission() {
        SubmissionDTO submissionDTO = new SubmissionDTO(
                LocalDate.of(2023, 07, 11),
                "Elcoxy",
                "Gaurav Pathak",
                "Jovil etc",
                "el1@em.com",
                "3528889602",
                "IBM Co.",
                "Kribhco",
                85.0,
                true,
                "Submitted",
                "Spandana",
                "Lokha Khadda"
        );
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/submission", submissionDTO, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was added successfully
    }

    @Test
    public void testGetSubmissions() throws Exception {
        SubmissionDTO submission1 = new SubmissionDTO(LocalDate.of(2023, 07, 11),
				"Elcoxy",
				"Gaurav Pathak",
				"Jovil etc",
				"elsd1@em.com",
				"3528889602",
				"IBM Co.",
				"Kribhco",
				85.0,
				true,
				"Submitted",
				"Spandana",
				"Lokha Khadda");
        SubmissionDTO submission2 = new SubmissionDTO(LocalDate.of(2023, 07, 11),
				"Elcoxy",
				"Gaurav Pathak",
				"Jovil etc",
				"elsd1@em.com",
				"3528889602",
				"IBM Co.",
				"Kribhco",
				85.0,
				true,
				"Submitted",
				"Spandana",
				"Lokha Khadda");
        List<SubmissionDTO> submissions = Arrays.asList(submission1, submission2);

        when(submissionService.getSubmissions()).thenReturn(submissions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/submission")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(submission1.getSubmissionId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(submission1.getConsultantName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(submission2.getSubmissionId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(submission2.getConsultantName()));
    }

    @Test
    public void testGetSubmissionsEmptyList() {
        when(submissionService.getSubmissions()).thenReturn(Collections.emptyList());

        SubmissionController submissionController = new SubmissionController(submissionService);
        List<SubmissionDTO> submissions = submissionController.getSubmissions();

        assertEquals(0, submissions.size());
    }

    @Test
    public void testDeleteSubmission() {
        String submissionId = "123"; // Specify an existing submission ID

        SubmissionController submissionController = new SubmissionController(submissionService);
        submissionController.deleteStudent(submissionId);

        ArgumentCaptor<String> submissionIdCaptor = ArgumentCaptor.forClass(String.class);
        verify(submissionService).removeSubmission(submissionIdCaptor.capture());

        String capturedSubmissionId = submissionIdCaptor.getValue();
        assertEquals(submissionId, capturedSubmissionId);
    }

    @Test
    public void testUpdateSubmission() throws Throwable {
        String submissionId = "123"; // Specify an existing submission ID
        String newName = "New Name"; // Specify the new name

        SubmissionController submissionController = new SubmissionController(submissionService);
        submissionController.updateStudent(submissionId, newName);

        ArgumentCaptor<String> submissionIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        verify(submissionService).updateStudent(submissionIdCaptor.capture(), nameCaptor.capture());

        String capturedSubmissionId = submissionIdCaptor.getValue();
        String capturedName = nameCaptor.getValue();
        assertEquals(submissionId, capturedSubmissionId);
        assertEquals(newName, capturedName);
    }

    @Test
    public void testUpdateSubmissionException() throws Throwable {
        String submissionId = "123"; // Specify an existing submission ID
        String newName = "New Name"; // Specify the new name

        doThrow(new RuntimeException("Error updating submission")).when(submissionService).updateStudent(submissionId, newName);

        SubmissionController submissionController = new SubmissionController(submissionService);
        assertThrows(RuntimeException.class, () -> submissionController.updateStudent(submissionId, newName));
    }

    @Test
    public void testGetSubmissions_EmptyList() {
        // Clear any existing submissions
//		submissionService.clearSubmissions();

        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/submission", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testAddNewSubmission_ValidSubmission() {
        SubmissionDTO submissionDTO = new SubmissionDTO(LocalDate.of(2023, 07, 11),
				"Elcoxy",
				"Gaurav Pathak",
				"Jovil etc",
				"elsdf1@em.com",
				"3528889602",
				"IBM Co.",
				"Kribhco",
				85.0,
				true,
				"Submitted",
				"Spandana",
				"Lokha Khadda");
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/submission", submissionDTO, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was added successfully
    }

    @Test
    public void testDeleteSubmission_ValidSubmissionId() {
        String submissionId = "123"; // Specify an existing submission ID
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/submission/" + submissionId, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was deleted successfully
    }

    @Test
    public void testUpdateSubmission_ValidSubmissionIdAndName() {
        String submissionId = "123"; // Specify an existing submission ID
        String newName = "New Name"; // Specify the new name
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/submission/" + submissionId + "?name=" + newName, HttpMethod.PUT, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was updated successfully
    }

    @Test
    public void testUpdateSubmission_ValidSubmissionIdWithoutNameParameter() {
        String submissionId = "123"; // Specify an existing submission ID
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/submission/" + submissionId, HttpMethod.PUT, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was updated successfully
        // with the existing name or without any changes
    }

    @Test
    public void testUpdateSubmission_NameExceedsMaxLength() {
        String submissionId = "123"; // Specify an existing submission ID
        String longName = "This is a very long name that exceeds the maximum allowed length"; // Provide a name that exceeds the allowed length
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/submission/" + submissionId + "?name=" + longName, HttpMethod.PUT, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions to verify the submission was not updated due to the long name
    }
}
