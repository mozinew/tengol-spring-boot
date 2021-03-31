package com.tengol.boot.hello.web;

import com.alibaba.fastjson.JSON;
import com.tengol.boot.hello.config.HelloProperties;
import com.tengol.boot.hello.dto.Param;
import com.tengol.boot.hello.service.ImageService;
import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImageController
 *
 * @author dongrui
 * @date 2020/11/6 9:41
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/water")
    public String watermark(@RequestParam("file") MultipartFile file) throws IOException {
        String path = "d://";
        //水印
        Thumbnails.of(path + "//img_test_1.jpg")
                .size(1024, 682)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path + "//img_test_1.jpg")), 0.5f)
                .toFile(path + "//thumb//img_new_1.jpg");

        return "deal ok";
    }


}
