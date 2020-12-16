package com.h2.hotelmangement.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /** Indicates that client cannot configure the [{@code data}] field */
    public static final String MSG_ERROR_DATA = "The [data] field is being assigned. It is not allowed because both [data] and [dataItems] cannot be assigned at the same time";

    /** Indicates that client cannot configure the [{@code dataItems}] field */
    public static final String MSG_ERROR_DATA_ITEMS = "The [dataItems] field field is being assigned. It is not allowed because both [data] and [dataItems] cannot be assigned at the same time";

    private int status = 200;

    @JsonIgnore
    private T data;

    @JsonIgnore
    private List<T> dataItems;

    private String message;
    private String internalError;
    private List<ErrorBean> errors;

    private Paging paging;

    /**
     * @return {@code data} or {@code dataItems} of T
     */
    @JsonProperty("data")
    private Object getData() {
        if (data != null) {
            return data;
        }
        return dataItems;
    }

    /**
     * @param builder a {@link ApiResponseBuilder}
     */
    private ApiResponse(ApiResponseBuilder<T> builder) {
        this.status = builder.status;
        this.data = builder.data;
        this.dataItems = builder.dataItems;
        this.paging = builder.paging;

        this.message = builder.message;
        this.internalError = builder.internalError;
        this.errors = builder.errors;
    }

    /**
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    /**
     * @param <T> the type of the output
     */
    public static class ApiResponseBuilder<T> {

        private int status = 200;

        private T data;
        private List<T> dataItems;

        private String message;
        private String internalError;
        private List<ErrorBean> errors;

        private Paging paging;

        /**
         * @param status the status value to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> status(int status) {
            if (status > 99) {
                this.status = status;
            }
            return this;
        }

        /**
         * @param data the {@code data} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> data(T data) {
            if (data != null && this.dataItems != null) {
                throw new IllegalArgumentException(MSG_ERROR_DATA);
            }
            this.data = data;
            return this;
        }

        /**
         * @param dataItems the list of {@code data} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> dataItems(List<T> dataItems) {
            if (dataItems != null && this.data != null) {
                throw new IllegalArgumentException(MSG_ERROR_DATA_ITEMS);
            }
            this.dataItems = dataItems;
            return this;
        }

        /**
         * @param paging the {@link Paging} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> paging(Paging paging) {
            this.paging = paging;
            return this;
        }

        /**
         * @param message
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * @param internalError
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> internalError(String internalError) {
            this.internalError = internalError;
            return this;
        }

        /**
         * @param errors
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> errors(List<ErrorBean> errors) {
            this.errors = errors;
            return this;
        }

        /**
         * @return a {@code ApiResponse}
         */
        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }

}
