package easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ColumnData
 *
 * @author dongrui
 * @date 2020/7/29 14:10
 */
@Data
@ColumnWidth(30)
@NoArgsConstructor
public class ColumnData {
    @ExcelProperty(value = "数据项",index = 0)
    private String itemName;

    @ExcelProperty(value = " ",index = 1)
    private String value1;

    @ExcelProperty(value = " ",index = 2)
    private String value2;

    @ExcelProperty(value = " ",index = 3)
    private String value3;

    @ExcelProperty(value = " ",index = 4)
    private String value4;

    public ColumnData(String itemName){
        this.itemName = itemName;
    }
}
