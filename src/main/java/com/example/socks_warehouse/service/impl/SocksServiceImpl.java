package com.example.socks_warehouse.service.impl;


import com.example.socks_warehouse.constant.ComparisonOperationEnum;
import com.example.socks_warehouse.dto.SocksDto;
import com.example.socks_warehouse.entity.Socks;
import com.example.socks_warehouse.exception.ColorNotFound;
import com.example.socks_warehouse.exception.SocksNotFound;
import com.example.socks_warehouse.mapper.SocksMapper;
import com.example.socks_warehouse.repository.SocksRepository;
import com.example.socks_warehouse.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final Logger logger = LoggerFactory.getLogger(SocksServiceImpl.class);
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;


    @Override
    public void incomeSocks(SocksDto socksDto) throws ColorNotFound {
        logger.info("Was invoked incomeSocks method");

        Socks addSocks;
        Optional<Socks> socksOptional = socksRepository
                .findByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (socksDto.getColor() == null) {
            throw new ColorNotFound();
        }
        if (socksOptional.isPresent()) {
            addSocks = socksOptional.get();
            addSocks.setQuantity(addSocks.getQuantity() + socksDto.getQuantity());
        } else {
            addSocks = socksMapper.toEntity(socksDto);
        }
        socksRepository.save(addSocks);

    }

    @Override
    public void outcomeSocks(SocksDto socksDto) throws SocksNotFound {
        logger.info("Was invoked outcomeSocks method");
        Socks socks = socksRepository.findByColorIgnoreCaseAndCottonPart(socksDto.getColor(), socksDto.getCottonPart())
                .orElseThrow();
        if (socks.getQuantity() < socksDto.getQuantity()) {
            throw new SocksNotFound();
        }
        socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
        socksRepository.save(socks);

    }

    @Override
    public Integer getSocks(String color, ComparisonOperationEnum operation, int cottonPart) {

        logger.info("Was invoked getSocks");
        Collection<Socks> socks = new ArrayList<>();
        switch (operation) {
            case equal:
                Optional<Socks> equalSocks = socksRepository.findByColorIgnoreCaseAndCottonPart(color, cottonPart);
                return equalSocks.get().getQuantity();
            case lessThan:
                socks = socksRepository.findByColorIgnoreCaseAndCottonPartBefore(color, cottonPart);
            case moreThan:
                socks = socksRepository.findByColorIgnoreCaseAndCottonPartAfter(color, cottonPart);
        }
        return socks.stream().map(Socks::getQuantity).reduce(0, Integer::sum);
    }
}
