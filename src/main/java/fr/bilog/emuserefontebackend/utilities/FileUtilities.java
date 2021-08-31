package fr.bilog.emuserefontebackend.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class FileUtilities {

    public static void saveArticleImage(String fileName,
                                        MultipartFile multipartFile) {
        String path = ImageConfig.uploadDirectory + "/article/";

        try {
            Files.deleteIfExists(Paths.get(path + fileName));
            multipartFile.transferTo(
                    new File(path + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteArticleImage(String fileName) {
        String path = ImageConfig.uploadDirectory + "/article/";
        try {
            Files.deleteIfExists(Paths.get(path + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveProvenanceImage(String fileName,
                                           MultipartFile multipartFile) {
        String path = ImageConfig.uploadDirectory + "/provenance/";

        try {
            Files.deleteIfExists(Paths.get(path + fileName));
            multipartFile.transferTo(
                    new File(path + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProvenanceImage(String fileName) {
        String path = ImageConfig.uploadDirectory + "/provenance/";
        try {
            Files.deleteIfExists(Paths.get(path + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
String path = MvcConfig.uploadDirectory;
	try {
				Path path_variable = Paths.get(path + "/" + productImage.getPath());
				Files.deleteIfExists(path_variable);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
 */
