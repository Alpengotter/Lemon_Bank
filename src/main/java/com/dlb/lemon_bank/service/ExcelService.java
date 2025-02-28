package com.dlb.lemon_bank.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.dlb.lemon_bank.domain.dto.UserExcelDto;
import com.dlb.lemon_bank.domain.entity.UserEntity;
import com.dlb.lemon_bank.domain.mapper.UserMapper;
import com.dlb.lemon_bank.domain.repository.UserRepository;
import com.dlb.lemon_bank.handler.LastRowStyleHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public byte[] generateExcelEmployees() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        List<UserEntity> allUsers = userRepository.findByIsActiveIsTrue();
        List<UserExcelDto> excelList = userMapper.toUserExcelDtoList(allUsers);
        // TODO Обдумать, что лучше, считать в цикле или сделать 2 доп запроса в бд
        Integer countLemons = 0;
        Integer countDiamonds = 0;
        for (UserExcelDto el : excelList) {
            if (Objects.nonNull(el.getCountLemons())) {
                countLemons += el.getCountLemons();
            }
            if (Objects.nonNull(el.getCountDiamonds())) {
                countDiamonds += el.getCountDiamonds();
            }
        }
        excelList.add(UserExcelDto.builder()
                .name("Итого")
                .countLemons(countLemons)
                .countDiamonds(countDiamonds)
            .build());

        WriteCellStyle headerStyle = new WriteCellStyle();
        WriteFont headerFont = new WriteFont();
        headerFont.setBold(true); // Жирный шрифт
        headerFont.setFontHeightInPoints((short) 9); // Размер шрифта
        headerStyle.setWriteFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Цвет фона
        WriteCellStyle contentStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headerStyle, contentStyle);

        ExcelWriter excelWriter = EasyExcel.write(outputStream, UserExcelDto.class)
            .registerWriteHandler(styleStrategy)
            .registerWriteHandler(new LastRowStyleHandler(excelList.size()))
            .build();

        WriteSheet writeSheet = EasyExcel.writerSheet("Отчет")
//            .includeColumnFieldNames(Arrays.asList("ФИО", "Кол-во лимонов", "Кол-во алмазов"))
            .build();

        excelWriter.write(excelList, writeSheet);

//        Sheet sheet = excelWriter.writeContext().writeSheetHolder().getSheet();
//        for (int i = 0; i < excelList.get(0).getClass().getDeclaredFields().length; i++) {
//            sheet.autoSizeColumn(i);
//        }

        // Завершаем запись
        excelWriter.finish();

        return outputStream.toByteArray();
    }
}
