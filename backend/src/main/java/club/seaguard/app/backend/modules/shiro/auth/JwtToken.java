package club.seaguard.app.backend.modules.shiro.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT认证实现
 *
 * @author WaTony Weng
 * @date 2019-07-15
 */

public class JwtToken implements AuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

}
