package resources;

import ejb.TestManagerBean;
import ejb.interfaces.TestManagerBeanLocal;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Michal on 21.03.2019
 */

@Path("/hello")
public class RestController {
    @EJB
    private TestManagerBeanLocal testManagerBean;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayPlainTextHello() {
        testManagerBean.doNothing();

        return "Hello Jersey";
    }
}
