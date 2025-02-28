package com.dlb.lemon_bank.controller;

import com.dlb.lemon_bank.domain.dto.HistoryResponseDto;
import com.dlb.lemon_bank.domain.dto.JwtRequestDto;
import com.dlb.lemon_bank.service.ExcelService;
import com.dlb.lemon_bank.service.HistoryService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ExcelService excelService;

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadExcel() throws IOException {
        List<JwtRequestDto> dtos = List.of(new JwtRequestDto("1", "2"));
        byte[] excelBytes = excelService.generateExcel(dtos);
        ByteArrayResource resource = new ByteArrayResource(excelBytes);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=your_dtos.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(resource);
    }

}
