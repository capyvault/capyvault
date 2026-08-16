package dev.capyvault.commonweb.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(

        String field,

        String code,

        String message

) {
}