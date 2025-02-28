package com.dlb.lemon_bank.domain.mapper.service;

import com.dlb.lemon_bank.domain.entity.UserEntity;
import com.dlb.lemon_bank.domain.repository.UserRepository;
import com.dlb.lemon_bank.handler.ErrorType;
import com.dlb.lemon_bank.handler.exception.LemonBankException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalitiqueMapperService {
    private final UserRepository userRepository;

    @Named("mapType")
    public String mapType(String email) {
        return new String("");
    }

}
