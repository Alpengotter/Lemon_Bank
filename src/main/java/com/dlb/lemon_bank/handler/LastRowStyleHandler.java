package com.dlb.lemon_bank.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

// TODO ПОД ВЫПИЛ, НЕ РАБОТАЕТ НИХУЯ
public class LastRowStyleHandler implements CellWriteHandler {
    private final int lastRowIndex;

    public LastRowStyleHandler(int totalRows) {
        this.lastRowIndex = totalRows; // Индекс последней строки
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder,
        WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell,
        Head head, Integer relativeRowIndex, Boolean isHead) {
        // Проверяем, является ли текущая строка последней
        if (relativeRowIndex != null && relativeRowIndex == lastRowIndex - 1) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();

            // Создаем стиль для шрифта
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true); // Жирный шрифт
            font.setColor(IndexedColors.RED.getIndex()); // Красный цвет шрифта
            cellStyle.setFont(font);

            // Применяем стиль к ячейке
            cell.setCellStyle(cellStyle);
        }
    }
}
