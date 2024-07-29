package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.model.UserReadHistory;
import com.patika.bloghubservice.service.UserReadHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/me")
public class UserReadHistoryController {

    private final UserReadHistoryService readHistoryService;

    public UserReadHistoryController(UserReadHistoryService readHistoryService) {
        this.readHistoryService = readHistoryService;
    }

    @GetMapping("/reading-history")
    public GenericResponse<List<UserReadHistory>> getReadHistory()
    {
        return GenericResponse.success(readHistoryService.getReadHistory(), HttpStatus.OK);
    }

}
