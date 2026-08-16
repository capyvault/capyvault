package dev.capyvault.commonweb.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(

        boolean success,

        String code,

        String message,

        List<ApiError> errors,

        String traceId,

        OffsetDateTime timestamp

) {
}
