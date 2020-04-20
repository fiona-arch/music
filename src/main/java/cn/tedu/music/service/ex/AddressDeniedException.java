package cn.tedu.music.service.ex;

public class AddressDeniedException extends ServiceException{
    public AddressDeniedException() {
    }

    public AddressDeniedException(String message) {
        super(message);
    }

    public AddressDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressDeniedException(Throwable cause) {
        super(cause);
    }

    public AddressDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
