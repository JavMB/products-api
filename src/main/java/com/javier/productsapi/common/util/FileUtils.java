package com.javier.productsapi.common.util;

import com.javier.productsapi.product.application.command.create.CreateProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUtils {

    public String saveProductImage(MultipartFile file) {

        String uniqueFileName;

        try (InputStream inputStream = file.getInputStream()) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            uniqueFileName = UUID.randomUUID().toString().concat("-").concat(filename);

            Path path = Path.of("uploads/products/");


            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING); // podriamos usar un bufferwriter


        } catch (Exception e) {
            throw new RuntimeException("Error uploading file");
        }
        return uniqueFileName;
    }


}
