package fr.bilog.emuserefontebackend.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
@Log4j2
public class ImageConfig implements WebMvcConfigurer {

    // @Value("${images.path}")
    public static String uploadDirectory = System.getProperty("user.home") + "/images";

    //public static String uploadDirectory = "";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info(" images path ::: " + uploadDirectory);
        registry.addResourceHandler("/staticfiles/**", "resources/**")
                .addResourceLocations("file:" + uploadDirectory + "\\", "classpath:/static/");
    }
}
