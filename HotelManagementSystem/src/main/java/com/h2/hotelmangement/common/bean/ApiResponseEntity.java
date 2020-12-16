package com.h2.hotelmangement.common.bean;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiResponseEntity<T> {

    private static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
    private HttpStatus httpStatus = HttpStatus.OK;
    private ApiResponse<T> apiResponse;

    /**
     * Creates a {@code ResponseEntity} of T
     *
     * @return a {@link ResponseEntity} of {@code ApiResponse<T>}
     */
    public ResponseEntity<ApiResponse<T>> entity() {
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    /**
     * Creates an {@code ResponseEntity} of {@code Object}
     *
     * @return a {@link ResponseEntity} of {@code ApiResponse<Object>}
     */
    public ResponseEntity<Object> entityObject() {
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    /**
     * Converts an object of {@code ApiResponse<T>} to JSON
     *
     * @return a JSON string if it is converted successfully
     * @throws IOException
     */
    public String toJson() throws IOException {
        return objectMapper.writeValueAsString(apiResponse);
    }

    /**
     * Writes the {@code ApiResponse<T>} to JSON. The {@code response} will be closed after data written
     *
     * @param response a {@link HttpServletResponse}
     * @throws IOException
     */
    public void writeJson(HttpServletResponse response) throws IOException {
        response.setStatus(response.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        }
        catch (JsonProcessingException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * @return the {@link HttpStatus}
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the apiResponse of T
     */
    public ApiResponse<T> getApiResponse() {
        return apiResponse;
    }

    /**
     * @param builder a {@link ApiResponseEntityBuilder}
     */
    private ApiResponseEntity(ApiResponseEntityBuilder<T> builder) {
        this.httpStatus = builder.httpStatus;
        this.apiResponse = ApiResponse.<T> builder().status(builder.status).data(builder.data)
                .dataItems(builder.dataItems).paging(builder.paging).errors(builder.errors).message(builder.message)
                .internalError(builder.internalError).build();
    }

    /**
     * @return an instance of {@code ApiResponseEntityBuilder}
     */
    public static <T> ApiResponseEntityBuilder<T> builder() {
        return new ApiResponseEntityBuilder<>();
    }

    /**
     *
     * @param <T> the type of the output
     */
    public static class ApiResponseEntityBuilder<T> {

        private int status = HttpStatus.OK.value();
        private HttpStatus httpStatus = HttpStatus.OK;

        private T data;
        private List<T> dataItems;

        private String message;
        private String internalError;
        private List<ErrorBean> errors;

        private Paging paging;

        /**
         * @param status the status value to set. The {@code status} value will not be set if its value less than
         *            {@code 100}
         * @return a {@code ApiResponseEntityBuilder}
         */
        public ApiResponseEntityBuilder<T> status(int status) {
            if (status > 99) {
                this.status = status;
                this.httpStatus = HttpStatus.valueOf(status);
            }
            return this;
        }

        /**
         * @param data the {@code data} to set
         * @return a {@code ApiResponseEntityBuilder}
         */
        public ApiResponseEntityBuilder<T> data(T data) {
            if (data != null && this.dataItems != null) {
                throw new IllegalArgumentException(ApiResponse.MSG_ERROR_DATA);
            }
            this.data = data;
            return this;
        }

        /**
         * @param dataItems the list of {@code data} to set
         * @return a {@code ApiResponseEntityBuilder}
         */
        public ApiResponseEntityBuilder<T> dataItems(List<T> dataItems) {
            if (dataItems != null && this.data != null) {
                throw new IllegalArgumentException(ApiResponse.MSG_ERROR_DATA_ITEMS);
            }
            this.dataItems = dataItems;
            return this;
        }

        /**
         * @param paging the {@link Paging} to set
         * @return a {@code ApiResponseEntityBuilder}
         */
        public ApiResponseEntityBuilder<T> paging(Paging paging) {
            this.paging = paging;
            return this;
        }

        /**
         * @param offset
         * @param limit
         * @param total
         * @return
         */
        public ApiResponseEntityBuilder<T> paging(long offset, int limit, long total) {
            if (this.paging != null) {
                throw new IllegalArgumentException("The [paging] field already configured");
            }
            this.paging = new Paging(offset, limit, total);
            return this;
        }

        /**
         * @param message the {@code message} value to set
         * @return a {@code ApiResponseEntityBuilder}
         */

        public ApiResponseEntityBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * @paramdata the {@code internalError} to set
         * @return a {@code ApiResponseEntityBuilder}
         */

        public ApiResponseEntityBuilder<T> internalError(String internalError) {
            this.internalError = internalError;
            return this;
        }

        /**
         * @param errors the list of {@code ErrorBean} to set
         * @return a {@code ApiResponseEntityBuilder}
         */
        public ApiResponseEntityBuilder<T> errors(List<ErrorBean> errors) {
            if (errors != null && !errors.isEmpty()) {
                this.errors = errors;
            }
            return this;
        }

        /**
         * @return a {@code ApiResponseEntity}
         */
        public ApiResponseEntity<T> build() {
            return new ApiResponseEntity<>(this);
        }
    }
}

