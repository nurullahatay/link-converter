package com.trendyol.linkconverter.configuration.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private String title;
    private Integer status;
    private String message;
}