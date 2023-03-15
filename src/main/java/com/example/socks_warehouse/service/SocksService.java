package com.example.socks_warehouse.service;

import com.example.socks_warehouse.constant.ComparisonOperationEnum;
import com.example.socks_warehouse.dto.SocksDto;
import com.example.socks_warehouse.exception.ColorNotFound;
import com.example.socks_warehouse.exception.SocksNotFound;

public interface SocksService {


    void incomeSocks(SocksDto socksDto) throws ColorNotFound;

    void outcomeSocks(SocksDto socksDto) throws SocksNotFound;

    int getSocks(String color, ComparisonOperationEnum operation, Integer cottonPart);
}
