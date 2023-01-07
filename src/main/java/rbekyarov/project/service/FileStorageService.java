package rbekyarov.project.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public void save(MultipartFile file, String filename);
    public Resource load(String filename);
    public void delete(String filename);
}
