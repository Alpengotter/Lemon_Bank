package com.dlb.lemon_bank.domain.mapper;


import com.dlb.lemon_bank.domain.dto.ExcelDateFilterDto;
import com.dlb.lemon_bank.domain.dto.HistoryExcelDto;
import com.dlb.lemon_bank.domain.dto.HistoryResponseDto;
import com.dlb.lemon_bank.domain.entity.HistoryEntity;
import com.dlb.lemon_bank.domain.mapper.service.HistoryMapperService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = HistoryMapperService.class
)
public interface HistoryMapper {

    @Mapping(target = "userId", source = "historyEntity.user.id")
    @Mapping(target = "adminId", source = "historyEntity.admin.id")
    @Mapping(target = "orderId", source = "historyEntity.order.id")
    HistoryResponseDto toHistoryResponseDto(HistoryEntity historyEntity);

    List<HistoryResponseDto> toHistoryResponseDtoList(List<HistoryEntity> historyEntity);

    @Mapping(target = "month", source = "filterDto.month", qualifiedByName = "mapDateToMonth")
    @Mapping(target = "countLemonsSpend", source = "filterDto", qualifiedByName = "mapCountLemonsSpend")
    @Mapping(target = "countLemonsAccrued", source = "filterDto", qualifiedByName = "mapCountLemonsAccrued")
    @Mapping(target = "countDiamondsSpend", source = "filterDto", qualifiedByName = "mapCountDiamondsSpend")
    @Mapping(target = "countDiamondsAccrued", source = "filterDto", qualifiedByName = "mapCountDiamondsAccrued")
    HistoryExcelDto toHistoryExcelDto(ExcelDateFilterDto filterDto);

    List<HistoryExcelDto> toHistoryExcelDtoList(List<ExcelDateFilterDto> excelDateFilterDtoList);

}
