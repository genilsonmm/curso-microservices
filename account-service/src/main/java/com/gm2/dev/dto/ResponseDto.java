package com.gm2.dev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)  //Se for null, irá excluir essa propriedade
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private String port;
}
