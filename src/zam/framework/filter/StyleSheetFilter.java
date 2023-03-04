/**
 *
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import zam.framework.enums.SysOutTag;
import zam.framework.util.DateUtil;

public class StyleSheetFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		String cn = getClass().getName();
		String pn = sc.getInitParameter("PROJECT_NAME");
		String INFO = SysOutTag.INFO+" ["+pn+"] "+cn;
		System.out.println(du.getSysDate()+" "+INFO+", Initialized.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		ServletContext sc = request.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		String cn = getClass().getName();
		String pn = sc.getInitParameter("PROJECT_NAME");
		String INFO = SysOutTag.INFO+" ["+pn+"] "+cn;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String uri = httpRequest.getRequestURI().toString();
		System.out.println(du.getSysDate()+" "+INFO+", URI = "+uri);
		
		System.out.println(du.getSysDate()+" "+INFO+", ContentType[1] = "+response.getContentType());			
		if (uri.endsWith(".css")) {
			response.setCharacterEncoding("UTF-8");
            response.setContentType("text/css; charset=UTF-8");			
		}
		System.out.println(du.getSysDate()+" "+INFO+", contentType[2] = "+response.getContentType());			
		// Continue to destination.
		chain.doFilter(request, response);
	}

}
