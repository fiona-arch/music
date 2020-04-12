package cn.tedu.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import javax.xml.crypto.Data;

@SpringBootApplication
@MapperScan("cn.tedu.music.mapper")
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }
    @Bean
    public MultipartConfigElement a(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        DataSize maxSize= DataSize.ofMegabytes(100);
        factory.setMaxFileSize(maxSize);
        DataSize maxRequestSize=DataSize.ofMegabytes(200);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }

}
