package dev.capyvault.commonweb.response;
import dev.capyvault.commonweb.code.ResponseCode;
import org.slf4j.MDC;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public final class ApiResponses {

    private ApiResponses() {
    }

    public static <T> ApiResponse<T> success(
            ResponseCode responseCode,
            T data
    ) {

        return new ApiResponse<>(
                true,
                responseCode.code(),
                responseCode.message(),
                data,
                null,
                traceId(),
                OffsetDateTime.now(ZoneOffset.UTC)
        );
    }

    public static <T> ApiResponse<T> success(
            ResponseCode responseCode,
            T data,
            ApiMeta meta
    ) {

        return new ApiResponse<>(
                true,
                responseCode.code(),
                responseCode.message(),
                data,
                meta,
                traceId(),
                OffsetDateTime.now(ZoneOffset.UTC)
        );
    }

    public static ApiErrorResponse error(
            ResponseCode responseCode
    ) {

        return new ApiErrorResponse(
                false,
                responseCode.code(),
                responseCode.message(),
                List.of(),
                traceId(),
                OffsetDateTime.now(ZoneOffset.UTC)
        );
    }

    public static ApiErrorResponse error(
            ResponseCode responseCode,
            List<ApiError> errors
    ) {

        return new ApiErrorResponse(
                false,
                responseCode.code(),
                responseCode.message(),
                errors,
                traceId(),
                OffsetDateTime.now(ZoneOffset.UTC)
        );
    }

    private static String traceId() {

        String traceId = MDC.get("traceId");

        return traceId != null
                ? traceId
                : "N/A";
    }
}
