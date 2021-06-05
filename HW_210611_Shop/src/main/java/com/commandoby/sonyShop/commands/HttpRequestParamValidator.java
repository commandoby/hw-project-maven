package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.exceptions.RequestParamNullException;

public class HttpRequestParamValidator {
    public static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized!";

    public static void validateParamNotNull(String parameter) throws RequestParamNullException {
        if (parameter == null) {
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        }
    }
}
