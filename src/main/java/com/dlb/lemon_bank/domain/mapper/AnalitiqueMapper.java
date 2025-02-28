package com.dlb.lemon_bank.domain.mapper;


import com.dlb.lemon_bank.domain.dto.AnalitiqueResponseDto;
import com.dlb.lemon_bank.domain.dto.AnalitiqueSummaryResponseDto;
import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import com.dlb.lemon_bank.domain.mapper.service.AnalitiqueMapperService;
import com.dlb.lemon_bank.domain.mapper.service.OrderMapperService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = AnalitiqueMapperService.class
)
public interface AnalitiqueMapper {
    AnalitiqueResponseDto toAnalitiqueResponseDto(AnalitiqueEntity analitiqueEntity);

    List<AnalitiqueResponseDto> toAnalitiqueResponseDtoList(List<AnalitiqueEntity> analitiqueEntityList);

    @Mapping(target = "type", source = "analitiqueEntity.type", qualifiedByName = "mapType")
    AnalitiqueSummaryResponseDto toAnalitiqueSummaryResponseDto(AnalitiqueEntity analitiqueEntity);
    List<AnalitiqueSummaryResponseDto> toAnalitiqueSummaryResponseDtoList(List<AnalitiqueEntity> analitiqueEntityList);

}
