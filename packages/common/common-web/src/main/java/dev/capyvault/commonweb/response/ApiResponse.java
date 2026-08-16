package dev.capyvault.commonweb.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(

        boolean success,

        String code,

        String message,

        T data,

        ApiMeta meta,

        String traceId,

        OffsetDateTime timestamp

) {
}
