package example.ejbserver.ejb.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.ejbserver.ejb.SampleStatelessBeanRemote;

/**
 * A servlet which injects a stateless EJB
 */
@WebServlet({"/", "/ejbserverServlet"})
public class EJBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @EJB(lookup = "corbaname::localhost:2819/NameService#ejb/global/ejbserver-ear/ejbserver-ejb/SampleStatelessBean!example.ejbserver.ejb.SampleStatelessBeanRemote")
    SampleStatelessBeanRemote statelessBean;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        // Call hello method on a stateless session bean
        String message = statelessBean.hello();

        writer.println(message);
    }
}
