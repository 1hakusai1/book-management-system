package com.example.bookmanagementsystem

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import org.testcontainers.containers.PostgreSQLContainer
import kotlin.test.assertEquals

typealias ResponseBody = Map<String, Any>

@SpringBootTest(
    properties = ["spring.datasource.url=jdbc:postgresql://localhost:5555/dev", "server.port=8081", "spring.flyway.clean-disabled=false"],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@Tag("integration")
class BookManagementSystemApplicationTests(@Autowired val flyway: Flyway) {

    lateinit var client: RestClient

    companion object {
        private val container = PostgreSQLContainer("postgres:15.8")
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("dev")

        @JvmStatic
        @BeforeAll
        fun startContainer() {
            container.portBindings = listOf("5555:5432")
            container.start()
        }

        @JvmStatic
        @AfterAll
        fun stopContainer() {
            container.stop()
        }
    }

    @BeforeEach
    fun setup() {
        flyway.clean()
        flyway.migrate()
        client = RestClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader("content-type", "application/json")
            .build()
    }

    @Test
    @DisplayName("著者の登録、更新、取得ができる")
    fun testAuthorCRUD() {
        val createAuthorResponse =
            client.post().uri("/author")
                .body(
                    mapOf(
                        "name" to "Martin Fowler",
                        "birthday" to "1963-12-18"
                    )
                )
                .retrieve().toEntity<ResponseBody>()

        val authorId = createAuthorResponse.body!!["id"]
        val created = client.get().uri("/author/$authorId").retrieve().toEntity<ResponseBody>().body!!
        assertEquals(authorId, created["id"])
        assertEquals("Martin Fowler", created["name"])
        assertEquals("1963-12-18", created["birthday"])

        client.put().uri("/author/$authorId")
            .body(
                mapOf(
                    "name" to "Kent Beck",
                    "birthday" to "1961-03-31"
                )
            )
            .retrieve()
        val updated = client.get().uri("/author/$authorId").retrieve().toEntity<ResponseBody>().body!!
        assertEquals(authorId, updated["id"])
        assertEquals("Kent Beck", updated["name"])
        assertEquals("1961-03-31", updated["birthday"])
    }

    @Test
    @DisplayName("書籍の登録、更新、取得ができる")
    fun testBookCRUD() {
        val createAuthorResponse = client.post().uri("/author")
            .body(mapOf("name" to "Kent Beck", "birthday" to "1961-03-31"))
            .retrieve().toEntity<ResponseBody>()
        val authorId = createAuthorResponse.body!!["id"]
        val createBookResponse = client.post().uri("/book")
            .body(
                mapOf(
                    "title" to "Test Driven Development",
                    "price" to 3080,
                    "publicationStatus" to "UNPUBLISHED",
                    "authorIds" to listOf(authorId)
                )
            )
            .retrieve().toEntity<ResponseBody>()
        val bookId = createBookResponse.body!!["id"]

        val created = client.get().uri("/book/$bookId").retrieve().toEntity<ResponseBody>().body!!
        assertEquals(bookId, created["id"])
        assertEquals("Test Driven Development", created["title"])
        assertEquals(3080, created["price"])
        assertEquals("UNPUBLISHED", created["publicationStatus"])
        assertEquals(listOf(authorId), created["authorIds"])

        client.put().uri("/book/$bookId")
            .body(
                mapOf(
                    "title" to "Extreme Programming",
                    "price" to 2420,
                    "publicationStatus" to "PUBLISHED",
                    "authorIds" to listOf(authorId)
                )
            )
            .retrieve()
        val updated = client.get().uri("/book/$bookId").retrieve().toEntity<ResponseBody>().body!!
        assertEquals(bookId, updated["id"])
        assertEquals("Extreme Programming", updated["title"])
        assertEquals(2420, updated["price"])
        assertEquals("PUBLISHED", updated["publicationStatus"])
        assertEquals(listOf(authorId), updated["authorIds"])
    }
}
