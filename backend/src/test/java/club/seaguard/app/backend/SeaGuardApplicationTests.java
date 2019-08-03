package club.seaguard.app.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 启动测试类
 *
 * @author WaTony Weng
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeaGuardApplicationTests {

  /**
   * 加载上下文
   */
  @Test
  public void contextLoads() {
    System.out.println("load context successfully");
  }

}
