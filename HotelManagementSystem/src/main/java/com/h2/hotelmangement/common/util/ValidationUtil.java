package com.h2.hotelmangement.common.util;

import com.h2.hotelmangement.common.bean.ErrorBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidationUtil {

    /**
     * The Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationUtil.class);

    /**
     * Prevents developers from initiating instance.
     */
    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param object
     * @return a list of {@code } if error occurs. Otherwise return an {@code empty} list
     */
    public static <T> List<ErrorBean> validateObject(T object) {
        List<ErrorBean> listError = new ArrayList<>();
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(object);
            listError = new ArrayList<>(violations.size());
            for (ConstraintViolation<?> violation : violations) {
                ErrorBean error = new ErrorBean();
                error.setCode(violation.getMessage());
                error.setMessage(violation.getMessage());
                listError.add(error);
            }
        }
        catch (Exception e) {
            LOG.error("getListError - error {} when validating object", e.getMessage());
            ErrorBean error = new ErrorBean();
            error.setMessage(e.getMessage());
            listError.add(error);
        }
        return listError;
    }
}
