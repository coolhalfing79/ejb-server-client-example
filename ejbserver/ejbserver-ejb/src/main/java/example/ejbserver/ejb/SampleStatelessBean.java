package example.ejbserver.ejb;

import javax.ejb.Stateless;
import javax.ejb.Remote;

@Stateless
@Remote(SampleStatelessBeanRemote.class)
public class SampleStatelessBean implements SampleStatelessBeanRemote {

    @Override
    public String hello() {
        return "Hello EJB World.";
    }
}
