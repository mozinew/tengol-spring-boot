package easyexcel.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import easyexcel.dto.ColumnData;
import easyexcel.dto.ScoreDTO;
import easyexcel.handler.ExcelColorHandler;
import easyexcel.handler.MyCellWriteHandler;
import easyexcel.handler.MySheetWriteHandler;
import easyexcel.service.DrivingBehaviorCompareService;
import easyexcel.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.*;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2020/7/29 11:14
 */
@Slf4j
@RestController
@RequestMapping("/hello/excel")
public abstract class HelloController {
    @Resource
    private DrivingBehaviorCompareService drivingBehaviorCompareService;

    private ApplicationContext applicationContext;


    @Value("${hello.bbc}")
    private String hello;

    @GetMapping("/")
    public String sayHello(@RequestParam(value = "name",required = false,defaultValue = "World") String name) {
        log.info("sayHello : Hello {}", name);
        HelloService helloService = getHelloService();
        return helloService.sayHello(name);
    }

    @Lookup
    public abstract HelloService getHelloService();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/export")
    public void export(@RequestParam(value = "name", required = false, defaultValue = "hello-excel") String fileName, HttpServletResponse response) throws IOException {
        // 设置浏览器响应格式
        processResponse(fileName, response);
        // 获取业务数据
        List<ScoreDTO> data = drivingBehaviorCompareService.getData();
        // 导出文件
        EasyExcel.write(response.getOutputStream(), ScoreDTO.class).sheet("Sheet1").doWrite(data);
    }

    private void processResponse(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置浏览器响应格式
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }

    @GetMapping("/export/color")
    public void exportByColor(@RequestParam(value = "name", required = false, defaultValue = "hello-excel-color") String fileName, HttpServletResponse response) throws IOException {
        // 设置浏览器响应格式
        processResponse(fileName, response);
        // 获取业务数据
        List<ColumnData> columnData = drivingBehaviorCompareService.getDataByColumn();
        // 导出文件
        EasyExcel.write(response.getOutputStream(), ColumnData.class)
                // 注册自定义拦截器
                .inMemory(Boolean.FALSE).registerWriteHandler(new ExcelColorHandler())
                .sheet("Sheet1")
                .doWrite(columnData);
    }

    @GetMapping("/export/template")
    public void exportByTemplate(@RequestParam(value = "name", required = false, defaultValue = "hello") String fileName,
                                 HttpServletResponse response) throws IOException {
        try {
            export0(fileName, response);
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    private void export0(String fileName, HttpServletResponse response) throws IOException {
        // 设置浏览器响应格式
        processResponse(fileName, response);
        // 获取业务数据
        List<ScoreDTO> data = drivingBehaviorCompareService.getData();
        // Excel 模板
        String templateFileName = "d://driving_behavior_template.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                //.registerWriteHandler(new ExcelColorHandler())
                .registerWriteHandler(new MySheetWriteHandler())
                .registerWriteHandler(new MyCellWriteHandler())
                .withTemplate(templateFileName)
                // 这里需要设置不关闭流
                .autoCloseStream(Boolean.FALSE)
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        excelWriter.fill(data, fillConfig, writeSheet);
        // 别忘记关闭流
        excelWriter.finish();
    }
}
