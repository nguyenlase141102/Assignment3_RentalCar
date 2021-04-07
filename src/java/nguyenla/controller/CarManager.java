/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ANH NGUYEN
 */
public class CarManager implements Filter {
    
    private static final boolean DEBUG = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public CarManager() {
    }    

    private final String HOMEPAGE = "LoadHomeServlet";
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
      
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url = HOMEPAGE;
        try {
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);

            if (resource.length() > 0) {
                url = resource.substring(0, 1).toUpperCase()
                        + resource.substring(1) + "Servlet";
                if (resource.lastIndexOf(".jsp") > 0) {
                    url = resource;
                }else if (resource.equalsIgnoreCase("LoginAccount")) {
                    url = resource + ".jsp";
                } else if (resource.equalsIgnoreCase("RentalCart")) {
                    url = resource + ".jsp";
                }else if (resource.equalsIgnoreCase("VerifyEmail")) {
                    url = resource + ".jsp";
                }else if (resource.equalsIgnoreCase("RegisterAccount")) {
                    url = resource + ".jsp";
                }
            }
            if (url != null) {
                RequestDispatcher dis = req.getRequestDispatcher(url);
                dis.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

   

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {                
                log("CarManager:Initializing filter");
            }
        }
    }

    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
