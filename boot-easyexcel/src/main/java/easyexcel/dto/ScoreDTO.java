package easyexcel.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ScoreDTO
 *
 * @author dongrui
 * @date 2020/7/29 11:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(32)
@HeadRowHeight(20)
public class ScoreDTO extends BaseRowModel {
    /**
     * 主键 ID
     */
    @ExcelIgnore
    private Integer id;

    /**
     * 课程名称
     */
    @ExcelProperty(value = "课程名称", index = 0)
    private String name;

    /**
     * 得分
     */
    @ExcelProperty(value = "考试分数", index = 1)
    private Integer score;

    /**
     * 考试时间
     */
    @ExcelProperty(value = "考试时间", index = 2)
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date time;
}
