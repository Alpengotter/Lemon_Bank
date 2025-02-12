package com.dlb.lemon_bank.controller;

import com.dlb.lemon_bank.domain.dto.AnalitiqueResponseDto;
import com.dlb.lemon_bank.service.AnalitiqueService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analitique")
@RequiredArgsConstructor
public class AnalitiqueController {

    private final AnalitiqueService analitiqueService;

    @GetMapping("/get-analitique")
    public List<AnalitiqueResponseDto> getAnalitique(
        @RequestParam("type") String type,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "month", required = false) Integer month,
        @RequestParam(value = "day", required = false) Integer day) {
        return analitiqueService.getAnalitique(type, year, month, day);
    }

}
