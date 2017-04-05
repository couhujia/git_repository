package hope.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import hope.server.domain.Authority;

@Service
public class FilterInvocationSecurityMetadataSourceImp implements FilterInvocationSecurityMetadataSource {

	private final AuthorityService authorityService;
	private HashMap<String, Collection<ConfigAttribute>> map = null;

	@Autowired
	public FilterInvocationSecurityMetadataSourceImp(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void loadResourceDefine() {
		map = new HashMap();
		Collection<ConfigAttribute> arrayAttribute;
		ConfigAttribute cfg;
		Collection<Authority> powers = this.authorityService.findAll();
		for (Authority au : powers) {
			arrayAttribute = new ArrayList<>();
			cfg = new SecurityConfig(au.getName());
			arrayAttribute.add(cfg);
			map.put(au.getUrl(), arrayAttribute);
		}

	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (null == map)
			loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if (matcher.matches(request)) {
				return map.get(resUrl);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
