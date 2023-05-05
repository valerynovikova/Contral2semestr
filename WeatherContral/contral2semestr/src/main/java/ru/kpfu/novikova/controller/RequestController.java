package ru.kpfu.novikova.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.novikova.dto.RequestDto;
import ru.kpfu.novikova.services.RequestService;

import java.util.List;
import java.util.Optional;

@RestController
public class RequestController {
    private final RequestService requestService;


    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @Operation(summary = "Return requests by user id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "returned requests", content = {@Content(mediaType = "application/json")})})

    @GetMapping("/request/by-user-id")
    public List<RequestDto> requestByUserId(@RequestParam Integer id) {
        return requestService.findAllByUserId(id);
    }

    @Operation(summary = "Return requests by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "returned requests", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/request/by-city")
    public List<RequestDto> requestByCity(@RequestParam String city) {
        return requestService.findAllByCity(city);
    }
}
