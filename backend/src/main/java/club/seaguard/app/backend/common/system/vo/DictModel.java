package club.seaguard.app.backend.common.system.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典值模型
 *
 * @author WaTony Weng
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictModel implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 字典值
   */
  private String value;

  /**
   * 字典文本
   */
  private String text;

  public DictModel() {
  }

  public DictModel(String value, String text) {
    this.value = value;
    this.text = text;
  }

}
