package org.gsafe;

import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("/coffre")
public class MockResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("deposer")
    public String deposer(@FormDataParam("file") InputStream file,//
            @FormDataParam("ID_CCFN") String ID_CCFN,//
            @FormDataParam("ID_UTI") String ID_UTI,//
            @FormDataParam("ID_CONT") String ID_CONT) {
        return "deposer";
    }

    @GET
    @Path("lire")
    public String lire(@QueryParam("ID_CCFN") String ccfn,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("IDU") String IDU) {
        return "lire";
    }

    @POST
    @Path("detruire")
    public String detruire(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("IDU") String IDU) {
        return "detruire";
    }

    @GET
    @Path("meta")
    public String meta(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("IDU") String IDU,//
            @QueryParam("ID_CONT") String ID_CONT) {
        return "meta";
    }

    @GET
    @Path("controler")
    public String ID_CONTroler(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("ID_CONT") String ID_CONT) {
        return "controler";
    }

    @GET
    @Path("journal")
    public String journal(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("IDU") String IDU,//
            @QueryParam("ID_CONT") String ID_CONT,//
            @QueryParam("DateEtHeure") String dateEtHeure,//
            @QueryParam("ID_ON_UTI") String ID_ON_UTI) {
        return "journal";
    }

    @GET
    @Path("lister")
    public String lister(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("IDU") String IDU,//
            @QueryParam("ID_CONT") String ID_CONT,//
            @QueryParam("DateEtHeure") String dateEtHeure,//
            @QueryParam("ID_ON_UTI") String ID_ON_UTIi) {
        return "lister";
    }

    @GET
    @Path("compter")
    public String compter(@QueryParam("ID_CCFN") String ID_CCFN,//
            @QueryParam("ID_UTI") String ID_UTI,//
            @QueryParam("ID_CONT") String ID_CONT,//
            @QueryParam("DateEtHeure") String dateEtHeure,//
            @QueryParam("ID_ON_UTI") String ID_ON_UTI) {
        return "compter";
    }
}
