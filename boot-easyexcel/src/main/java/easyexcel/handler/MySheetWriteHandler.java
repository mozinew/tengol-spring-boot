package easyexcel.handler;

import com.alibaba.excel.write.handler.AbstractSheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * SheetWriteHandler
 *
 * 不能实现颜色设置的功能
 *
 * @author dongrui
 * @date 2020/7/30 17:23
 */
@Slf4j
public class MySheetWriteHandler extends AbstractSheetWriteHandler {
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        System.out.println("=== afterSheetCreate start ===");
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setDefaultColumnWidth(27);
        System.out.println("== set default width : 50");
        System.out.println("=== afterSheetCreate end ===");
        System.out.println();
    }
}
