package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import rbekyarov.project.service.impl.FileStorageServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileStorageServiceImplTest {
    private final Path root = Paths.get("uploads");
    @Mock
    private Resource mockResource;
    @InjectMocks
    private FileStorageServiceImpl fileStorageService;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(root);
    }

    @Test
    void shouldSaveFile() throws Exception {
        // Given
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        // When
        fileStorageService.save(mockFile, "test.txt");

        // Then
        Path filePath = root.resolve("test.txt");
        assertTrue(Files.exists(filePath));
    }



}
