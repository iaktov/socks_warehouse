package com.example.socks_warehouse.service;

import com.example.socks_warehouse.constant.ComparisonOperationEnum;
import com.example.socks_warehouse.dto.SocksDto;

public interface SocksService {


    void incomeSocks(SocksDto socksDto);

    void outcomeSocks(SocksDto socksDto);

    int getSocks(String color, ComparisonOperationEnum operation, int cottonPart);
}
