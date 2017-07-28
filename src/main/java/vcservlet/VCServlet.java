/**
 * This class contains the methods that handle the HTTP requests coming from the front-end.
 *
 * @author Yordanka Ivanova
 * @since July 2017
 */

package vcservlet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * URL is http://localhost:8080/VC/rest/ for the server side
 */
@Path("/")
public class VCServlet {

    //a logger which prints to stdout
    private static Logger log;

    public VCServlet(){
        log = Logger.getLogger(getClass().getName());
    }

    //this method is here just to make sure the service is up and running
    //URL http://localhost:8080/VC/rest/getInfo
    @GET
    @Path("/getInfo")
    @Produces("text/html")
    public String sayHello(){
        return "Hello Jersey";
    }


    /**
     * @param input the JSON for an edge coming from the front-end upon the creation of a new edge
     * @return a response indicating whether the JSON has been processed and the relevant bits put into the database
     * URL: http://localhost:8080/VC/rest/postEdge
     */
    @Path("/postEdge")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getJSONInputEdge(String input){
        log.log(Level.INFO, "*** VERSION CONTROL SERVICE - New Edge Request ***");

        VCForkControl vcControl = new VCForkControl();
        String out = vcControl.evalJSONEdge(input);

        log.log(Level.INFO,"*** VERSION CONTROL SERVICE - Edge Request Handled ***");

        return out;
    }

    /**
     * @param input the JSON for a node coming from the front-end upon the creation of a new node
     * @return a response indicating whether the JSON has been processed and the relevant bits put into the database
     * URL: http://localhost:8080/VC/rest/postNode
     */
    @Path("/postNode")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getJSONInputNode(String input){
        log.log(Level.INFO, "*** VERSION CONTROL SERVICE - New Node Request ***");

        VCForkControl vcControl = new VCForkControl();
        String out = vcControl.evalJSONNode(input);

        log.log(Level.INFO,"*** VERSION CONTROL SERVICE - Node Request Handled ***");

        return out;
    }

}
