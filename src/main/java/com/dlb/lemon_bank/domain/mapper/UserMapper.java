package com.dlb.lemon_bank.domain.mapper;


import com.dlb.lemon_bank.domain.dto.UserExcelDto;
import com.dlb.lemon_bank.domain.dto.UserBaseDto;
import com.dlb.lemon_bank.domain.dto.UserResponseDto;
import com.dlb.lemon_bank.domain.entity.UserEntity;
import com.dlb.lemon_bank.domain.mapper.service.UserMapperService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UserMapperService.class)
public interface UserMapper {
    UserResponseDto toUserResponseDto(UserEntity entity);
    List<UserResponseDto> toListUserResponseDto(Page<UserEntity> entities);
    List<UserResponseDto> toListUserResponseDto(List<UserEntity> entities);
    @Mapping(target = "isActive", source = "userBaseDto.isActive", defaultValue = "true")
    UserEntity toUserEntity(UserBaseDto userBaseDto);

    @Mapping(target = "name", source = "userEntity", qualifiedByName = "mapFullName")
    @Mapping(target = "countLemons", source = "userEntity.lemons")
    @Mapping(target = "countDiamonds", source = "userEntity.diamonds")
    UserExcelDto toUserExcelDto(UserEntity userEntity);

    List<UserExcelDto> toUserExcelDtoList (List<UserEntity> userEntityList);

}
