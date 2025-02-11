package com.dlb.lemon_bank.controller;

import com.dlb.lemon_bank.domain.dto.OrderResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderUpdateStatusDto;
import com.dlb.lemon_bank.domain.dto.OrderWebhookDto;
import com.dlb.lemon_bank.service.AnalitiqueService;
import com.dlb.lemon_bank.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analitique")
@RequiredArgsConstructor
public class AnalitiqueController {
    private final AnalitiqueService analitiqueService;

//    @GetMapping("/create")
//    public OrderResponseDto createNewOrder(@RequestBody OrderWebhookDto orderDto) {
//        return ordersService.createNewOrder(orderDto);
//    }

}
