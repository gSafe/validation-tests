package org.gsafe.step;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MetadataSteps extends GsafeSteps {

    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When("$user récupère les métadonnées techniques du document identifié $onid dans le conteneur $conteneur du coffre $safe")
    public void readMeta(String user, String onid, String conteneur, String safe) throws IOException {
        try {
            response = mySafe.readMeta(onid, user, safe, conteneur);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------
    @Then("l'id de l'objet auquel correspond les métadonnées est $onid")
    public void checkMeta(String onid) {
        assertEquals(onid, response.idU);
    }
}
