package com.tengol.boot.hello;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * HelloTest
 *
 * @author dongrui
 * @date 2020/10/27 11:03
 */
public class HelloTest {
    @Test
    public void test1(){
        String f1 = "abc.abc.bbc";
        String agencyCode = newFilename(f1);
        System.out.println(agencyCode);
    }

    private String newFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (StringUtils.isEmpty(originalFilename)) {
            return uuid.concat(".txt");
        }
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        return uuid.concat(fileType);
    }
}
