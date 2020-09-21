package easyexcel.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.List;

/**
 * CellWriteHandler
 *
 * @author dongrui
 * @date 2020/7/30 17:17
 */
public class MyCellWriteHandler extends AbstractCellWriteHandler {
    private final static double GOOD = 75d;

    private Double baseVal;
    private CellStyle redCellStyle;
    private CellStyle greenCellStyle;
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        System.out.println("===== cell : rowIndex=" + rowIndex + ", columnIndex=" + columnIndex);
        // 课程分数 是 2 列
        if(rowIndex == 2 && columnIndex >= 1){
            double cellValue = cell.getNumericCellValue();
            System.out.println("== val : " + cellValue);
            if(columnIndex == 1){
                baseVal = cellValue;
                Sheet sheet = writeSheetHolder.getSheet();
                Font redFont = sheet.getWorkbook().createFont();
                redFont.setColor(IndexedColors.RED.index);
                redFont.setBold(true);
                Font greenFont = sheet.getWorkbook().createFont();
                greenFont.setColor(IndexedColors.GREEN.index);
                greenFont.setBold(true);
                this.redCellStyle = sheet.getWorkbook().createCellStyle();
                this.greenCellStyle = sheet.getWorkbook().createCellStyle();
                redCellStyle.setFont(redFont);
                greenCellStyle.setFont(greenFont);
            }else{
                if(cellValue > baseVal){
                    cell.setCellStyle(redCellStyle);
                    System.out.println("== color：red");
                }else{
                    cell.setCellStyle(greenCellStyle);
                    System.out.println("== color：green");
                }
            }
            // 插入批注（大于90，增加批注【优秀】）
            if (cellValue > GOOD) {
                Drawing<?> drawing = writeSheetHolder.getSheet().createDrawingPatriarch();
                // 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
                Comment comment =
                        drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) columnIndex, rowIndex, (short) columnIndex + 2, rowIndex + 8));
                // 输入批注信息
                comment.setString(new XSSFRichTextString("优秀!"));
                // 将批注添加到单元格对象中
                cell.setCellComment(comment);
            }
        }
        System.out.println("===== cell end ======");
        System.out.println();
    }

    private void backup(WriteSheetHolder writeSheetHolder, Cell cell) {
        int columnIndex = cell.getColumnIndex();
        int rowIndex = cell.getRowIndex();
        if(rowIndex == 1){
            if(columnIndex > 2){
                String stringCellValue = cell.getStringCellValue();
                long cellValue = Long.parseLong(stringCellValue);
                //如果第5列单元格内容大于120，则将字体样式设置为红色
                if(cellValue > 80){
                    Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                    CellStyle cellStyle = workbook.createCellStyle();
                    //设置单元格边框类型
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    //水平居中
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    Font font = workbook.createFont();
                    //字体设置为红色
                    font.setColor(IndexedColors.RED.index);
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
    }
}
