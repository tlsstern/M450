package ch.tbz.m450.testing.tools;

import ch.tbz.m450.testing.tools.repository.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// starts the whole application on a random port and calls the REST interface over HTTP
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getStudentsReturnsInitialStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/students", Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jonas", response.getBody()[0].getName());
        assertEquals("jonas@tbz.ch", response.getBody()[0].getEmail());
    }

    @Test
    void postStudentAddsStudentToList() {
        Student student = new Student("Thomas", "thomas@tbz.ch");

        ResponseEntity<Void> postResponse = restTemplate.postForEntity("/students", student, Void.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Student[] students = restTemplate.getForObject("/students", Student[].class);
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equals("Thomas") && s.getEmail().equals("thomas@tbz.ch")) {
                found = true;
            }
        }
        assertTrue(found);
    }
}
