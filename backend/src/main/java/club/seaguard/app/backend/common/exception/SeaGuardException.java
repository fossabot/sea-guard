package club.seaguard.app.backend.common.exception;

/**
 * 自定义运行异常
 *
 * @author WaTony Weng
 */

public class SeaGuardException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SeaGuardException(String message) {
    super(message);
  }

  public SeaGuardException(Throwable cause) {
    super(cause);
  }

  public SeaGuardException(String message, Throwable cause) {
    super(message, cause);
  }

}
