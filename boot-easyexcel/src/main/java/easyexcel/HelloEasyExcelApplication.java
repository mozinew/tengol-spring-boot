package easyexcel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot & EasyExcel
 *
 * @author dongrui
 * @date 2020/7/29 11:12
 */
@Slf4j
@SpringBootApplication
public class HelloEasyExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloEasyExcelApplication.class, args);
        log.info("HelloEasyExcelApplication start successfully ...");
    }
}
