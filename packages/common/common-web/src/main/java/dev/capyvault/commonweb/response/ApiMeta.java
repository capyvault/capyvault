package dev.capyvault.commonweb.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiMeta(

        Integer page,

        Integer size,

        Long totalElements,

        Integer totalPages,

        Boolean first,

        Boolean last

) {

    public static ApiMeta page(
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean first,
            boolean last
    ) {

        return new ApiMeta(
                page,
                size,
                totalElements,
                totalPages,
                first,
                last
        );
    }
}