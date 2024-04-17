package com.futureai.write.common.exception;

/**
 * @author max
 * @description 非法参数异常
 * @date 2024/3/4 16:29
 */
public class InvalidParameterException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    public InvalidParameterException(String message) {
        super(message);
    }

    /**
     * 提高性能
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}
