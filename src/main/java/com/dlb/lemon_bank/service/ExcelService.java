package com.dlb.lemon_bank.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.dlb.lemon_bank.domain.dto.ExcelDateFilterDto;
import com.dlb.lemon_bank.domain.dto.HistoryExcelDto;
import com.dlb.lemon_bank.domain.dto.OrdersExcelDto;
import com.dlb.lemon_bank.domain.dto.UserExcelDto;
import com.dlb.lemon_bank.domain.entity.UserEntity;
import com.dlb.lemon_bank.domain.mapper.HistoryMapper;
import com.dlb.lemon_bank.domain.mapper.OrderMapper;
import com.dlb.lemon_bank.domain.mapper.UserMapper;
import com.dlb.lemon_bank.domain.repository.UserRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final HistoryMapper historyMapper;

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
            .build();

        WriteSheet writeSheet = EasyExcel.writerSheet("Отчет")
            .build();

        excelWriter.write(excelList, writeSheet);


        // Завершаем запись
        excelWriter.finish();

        return outputStream.toByteArray();
    }

    public byte[] generateExcelOrders(Integer year) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        List<OrdersExcelDto> excelList = orderMapper.toOrdersExcelDtoList(
            generateOrderExcelFilterDtos(year));
        Integer countOrders = 0;
        for (OrdersExcelDto el : excelList) {
            countOrders += el.getCountOrders();
        }
        excelList.add(OrdersExcelDto.builder()
            .month("Итого")
            .countOrders(countOrders)
            .build());

        WriteCellStyle headerStyle = new WriteCellStyle();
        WriteFont headerFont = new WriteFont();
        headerFont.setBold(true); // Жирный шрифт
        headerFont.setFontHeightInPoints((short) 9); // Размер шрифта
        headerStyle.setWriteFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Цвет фона
        WriteCellStyle contentStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headerStyle, contentStyle);

        ExcelWriter excelWriter = EasyExcel.write(outputStream, OrdersExcelDto.class)
            .registerWriteHandler(styleStrategy)
//            .registerWriteHandler(new CustomCellWriteHandler())
            .build();

        WriteSheet writeSheet = EasyExcel.writerSheet("Отчет")
            .build();

        excelWriter.write(excelList, writeSheet);


        // Завершаем запись
        excelWriter.finish();

        return outputStream.toByteArray();
    }

    public byte[] generateExcelResourseTransactions(Integer year) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        List<HistoryExcelDto> excelList = historyMapper.toHistoryExcelDtoList(
            generateOrderExcelFilterDtos(year));

        Integer countLemonSpend = 0;
        Integer countLemonAccrued = 0;
        Integer countDiamondsSpend = 0;
        Integer countDiamondsAccrued = 0;
        for (HistoryExcelDto el : excelList) {
            countLemonSpend += el.getCountLemonsSpend();
            countLemonAccrued += el.getCountLemonsAccrued();
            countDiamondsSpend += el.getCountDiamondsSpend();
            countDiamondsAccrued += el.getCountDiamondsAccrued();
        }
        excelList.add(HistoryExcelDto.builder()
            .month("Итого")
            .countLemonsSpend(countLemonSpend)
                .countLemonsAccrued(countLemonAccrued)
                .countDiamondsSpend(countDiamondsSpend)
                .countDiamondsAccrued(countDiamondsAccrued)
            .build());

        WriteCellStyle headerStyle = new WriteCellStyle();
        WriteFont headerFont = new WriteFont();
        headerFont.setBold(true); // Жирный шрифт
        headerFont.setFontHeightInPoints((short) 9); // Размер шрифта
        headerStyle.setWriteFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Цвет фона
        WriteCellStyle contentStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headerStyle, contentStyle);

        ExcelWriter excelWriter = EasyExcel.write(outputStream, HistoryExcelDto.class)
            .registerWriteHandler(styleStrategy)
            .build();

        WriteSheet writeSheet = EasyExcel.writerSheet("Отчет")
            .build();

        excelWriter.write(excelList, writeSheet);


        // Завершаем запись
        excelWriter.finish();

        return outputStream.toByteArray();
    }


    private List<ExcelDateFilterDto> generateOrderExcelFilterDtos(Integer year) {
        List<ExcelDateFilterDto> filterDtos = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            filterDtos.add(ExcelDateFilterDto.builder()
                    .month(i)
                    .year(year)
                .build());
        }
        return filterDtos;
    }
}
