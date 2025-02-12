package com.dlb.lemon_bank.domain.mapper;


import com.dlb.lemon_bank.domain.dto.AnalitiqueResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderWebhookDto;
import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import com.dlb.lemon_bank.domain.entity.OrdersEntity;
import com.dlb.lemon_bank.domain.service.OrderMapperService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AnalitiqueMapper {
    AnalitiqueResponseDto toAnalitiqueResponseDto(AnalitiqueEntity analitiqueEntity);

    List<AnalitiqueResponseDto> toAnalitiqueResponseDtoList(List<AnalitiqueEntity> analitiqueResponseDtoList);

}
