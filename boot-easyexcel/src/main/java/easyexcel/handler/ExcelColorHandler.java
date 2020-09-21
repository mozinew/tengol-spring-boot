package easyexcel.handler;

import com.alibaba.excel.write.handler.AbstractRowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/**
 * ExcelExportHandler
 *
 * @author dongrui
 * @date 2020/7/29 14:04
 */
public class ExcelColorHandler extends AbstractRowWriteHandler {
    private final static double GOOD = 90d;

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        System.out.println("======== afterRowDispose ==========");
        process(writeSheetHolder, row, relativeRowIndex, isHead);
    }

    private void process(WriteSheetHolder writeSheetHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        if (!isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            Font redFont = sheet.getWorkbook().createFont();
            redFont.setColor(IndexedColors.RED.index);
            redFont.setBold(true);
            Font greenFont = sheet.getWorkbook().createFont();
            greenFont.setColor(IndexedColors.GREEN.index);
            greenFont.setBold(true);

            // 只对第二列【考试分数】进行设置，行号从 0 开始，标题行不算在内
            if (relativeRowIndex == 1) {
                // 参照值
                Cell refCell = row.getCell(1);
                double refVal = Double.parseDouble(refCell.getStringCellValue());

                for (int i = 1; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    // 获得数值
                    double val = Double.parseDouble(cell.getStringCellValue());
                    CellStyle style = sheet.getWorkbook().createCellStyle();
//                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 必须设置此项，否则无法显示背景色
                    // 设置背景颜色（大于参考值则红色，小于参考值则绿色）
                    if(i >= 2){
                        if (val > refVal) {
                            //style.setFillForegroundColor(IndexedColors.RED.getIndex());
                            style.setFont(redFont);
                        } else if (val < refVal) {
                            //style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                            style.setFont(greenFont);
                        }
                        cell.setCellStyle(style);
                    }

                    // 插入批注（大于90，增加批注【优秀】）
                    if (val > GOOD) {
                        Drawing<?> drawing = sheet.createDrawingPatriarch();
                        // 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
                        Comment comment =
                                drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) i, relativeRowIndex + 1, (short) i + 2, relativeRowIndex + 8));
                        // 输入批注信息
                        comment.setString(new XSSFRichTextString("优秀!"));
                        // 将批注添加到单元格对象中
                        cell.setCellComment(comment);
                    }

                }
            }
        }
    }
}
