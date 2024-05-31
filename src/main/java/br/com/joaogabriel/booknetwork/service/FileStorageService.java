package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.model.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String save(@NonNull MultipartFile file, @NonNull User user);
}
