package com.patika.bloghubservice.configuration;

import com.patika.bloghubservice.file.Storage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String projectDir = System.getProperty("user.dir");
        String uploadsDir = Paths.get(projectDir, "uploads").toAbsolutePath().toString();
        registry.addResourceHandler("/blog/**")
                .addResourceLocations("file:" + uploadsDir + "/blog/");
    }

    @Bean
    CommandLineRunner createStorageDirs(){
        return args -> {
            createFolder(Paths.get(Storage.getRoot()));
            createFolder(Paths.get(Storage.getRoot(), Storage.getImages()));
        };
    }

    private void createFolder(Path path){
        File file = path.toFile();
        boolean isFolderExist = file.exists() && file.isDirectory();
        if(!isFolderExist) {
            file.mkdir();
        }
    }
    
}