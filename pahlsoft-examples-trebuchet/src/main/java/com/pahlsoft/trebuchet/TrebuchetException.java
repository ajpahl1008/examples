package com.pahlsoft.trebuchet;

public class TrebuchetException extends Exception {
    public  TrebuchetException() {
    }

    public TrebuchetException(String message)
    {
        super(message);
    }

    public TrebuchetException(Throwable cause)
    {
        super(cause);
    }

    public TrebuchetException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public TrebuchetException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
