package com.example.videoVerseassignment.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws Exception{

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream=multipartFile.getInputStream()) {
            Path filePath=uploadPath.resolve(fileName);
            System.out.println(filePath.toString());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ioException){
            throw new Exception("could not save image "+fileName, ioException);
        }

    }

    public static void updateFile(String uploadDir, String oldFileName, String newFileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path oldFilePath = uploadPath.resolve(oldFileName);
            Path newFilePath = uploadPath.resolve(newFileName);
            Files.deleteIfExists(oldFilePath);
            Files.copy(inputStream, newFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not update file");
        }
    }

    public static void deleteFile(String uploadDir, String fileName) throws Exception{
        Path uploadPath=Paths.get(uploadDir);
        Path filePath=uploadPath.resolve(fileName);
        Files.deleteIfExists(filePath);
    }
}
