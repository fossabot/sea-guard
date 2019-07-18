package club.seaguard.app.backend.modules.shiro.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户值对象
 *
 * @author WaTony Weng
 * @date 2019-07-15
 */

@Data
public class UserBean {

    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();
    private Set<String> perms = new HashSet<>();

}