package easyexcel.util;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * ExcelUtil
 *
 * @author dongrui
 * @date 2020/7/29 11:20
 */
@Slf4j
public class ExcelUtil {
    private final static String EXCEL_SUFFIX = ".xlsx";
    private final static String DEFAULT_SHEET = "Sheet1";

    /**
     * 导出 Excel
     */
    public static <T,S> void export(String name, HttpServletResponse response, Class<T> head, List<S> data) {
        // 指定浏览器以附件形式下载
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        response.setHeader("content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("utf-8");
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            // 文件名
            String fileName = getFileName(name);
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // Sheet 名称
            String sheetName = getSheetName(name);
            // 导出
            EasyExcel.write(response.getOutputStream(), head)
                    .file(fileName)
                    .sheet(sheetName)
                    .doWrite(data);
        } catch (IOException ie) {
            log.error("导出失败：{}", ie.getMessage(), ie);
        }
    }

    private static String getFileName(String name) throws UnsupportedEncodingException {
        // 这里URLEncoder.encode可以防止中文乱码 当然和 easyexcel 没有关系
        String fileName = StringUtils.isEmpty(name) ? String.valueOf(System.currentTimeMillis()) : name;
        return URLEncoder.encode(fileName.concat(EXCEL_SUFFIX), "UTF-8");
    }

    private static String getSheetName(String name) throws UnsupportedEncodingException {
        String sheetName = StringUtils.isEmpty(name) ? DEFAULT_SHEET : name;
        return URLEncoder.encode(sheetName,"UTF-8");
    }
}
