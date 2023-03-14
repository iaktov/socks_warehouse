package com.example.socks_warehouse.controller;


import com.example.socks_warehouse.constant.ComparisonOperationEnum;
import com.example.socks_warehouse.dto.SocksDto;
import com.example.socks_warehouse.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {

    private final Logger logger = LoggerFactory.getLogger(SocksController.class);

    private final SocksService socksService;


    @Operation(summary = "getSocksFromWarehouse",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Integer.class))),
                    @ApiResponse(responseCode = "400", description = "Absence or presence request parameters"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller " +
                            "(for example, the database is unavailable)")
            })
    @GetMapping
    public ResponseEntity<Integer> getSocksFromWarehouse(@RequestParam(value = "color") String color,
                                                         @RequestParam(value = "operation") ComparisonOperationEnum operation,
                                                         @RequestParam(value = "cottonPart") int cottonPart) {
        logger.info("Processing getSocksFromWarehouse");
        return ResponseEntity.ok(socksService.getSocks(color, operation, cottonPart));
    }

    @Operation(summary = "incomeSocksToWarehouse",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Managed to add income"),
                    @ApiResponse(responseCode = "400", description = "Query parameters are missing or are not in the correct format"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller " +
                            "(for example, the database is unavailable).")
            })
    @PostMapping("/income")
    public ResponseEntity<SocksDto> incomeSocksToWarehouse(@RequestBody SocksDto socksDto) {
        logger.info("Was invoked incomeSocksToWarehouse method");
        socksService.incomeSocks(socksDto);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "outcomeSocksFromWarehouse",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Managed to carry out the release of socks"),
                    @ApiResponse(responseCode = "400", description = "Query parameters are missing or are not in the correct format"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller " +
                            "(for example, the database is unavailable).")
            })
    @PostMapping("/outcome")
    public ResponseEntity<SocksDto> outcomeSocksFromWarehouse(@RequestBody SocksDto socksDto) {
        logger.info("Was invoked outcomeSocksFromWarehouse method");
        socksService.outcomeSocks(socksDto);
        return ResponseEntity.ok().build();

    }


}
