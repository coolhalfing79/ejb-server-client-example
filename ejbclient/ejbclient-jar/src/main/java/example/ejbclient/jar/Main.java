package example.ejbclient.jar;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import example.ejbserver.ejb.SampleStatelessBean;
import example.ejbserver.ejb.SampleStatelessBeanRemote;

/**
 * Main
 */
public class Main {

    @EJB(lookup = "corbaname::ejbserver:2819/NameService#ejb/global/ejbserver-ear/ejbserver-ejb/SampleStatelessBean!example.ejbserver.ejb.SampleStatelessBeanRemote")
    static SampleStatelessBeanRemote bean;

    public static void main(String[] args) throws NamingException {
        String host = System.getenv("CORBA_HOST");
        String port = System.getenv("CORBA_PORT");
        if (host == null) {
            System.err.println("CORBA_HOST environment variable not provided. Using default");
            host = "ejbserver";
        }
        if (port == null) {
            System.err.println("CORBA_PORT environment variable not provided. Using default");
            port = "2819";
        }

        String nameService = "corbaname::" + host + ":" + port + "/NameService";
        String lookup = nameService + "#ejb/global/ejbserver-ear/ejbserver-ejb/";
        String beanView = SampleStatelessBean.class.getSimpleName() + "!" + SampleStatelessBeanRemote.class.getName();
        lookup = lookup + beanView.replace(".", "\\.");

        System.out.println("Lookup url: " + lookup);

        InitialContext c = new InitialContext();
        Object found = c.lookup(lookup);
        System.out.println(found);

        // or

        System.out.println(bean.hello());
    }
}
