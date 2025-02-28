package com.dlb.lemon_bank.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dlb.lemon_bank.domain.dto.JwtRequestDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
    public byte[] generateExcel(List<JwtRequestDto> dtos) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Создаем ExcelWriter
        ExcelWriter excelWriter = EasyExcel.write(outputStream, JwtRequestDto.class).build();

        // Создаем лист
        WriteSheet writeSheet = EasyExcel.writerSheet("YourDTOs").build();

        // Записываем данные
        excelWriter.write(dtos, writeSheet);

        // Завершаем запись
        excelWriter.finish();

        return outputStream.toByteArray();
    }

}
