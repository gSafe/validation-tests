package org.gsafe.step;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class DetruireSteps extends GsafeSteps {
    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When("$user détruit le document identifié $onid dans le coffre $safe")
    public void remove(String user, String onid, String safe) throws IOException {
        try {
            response = mySafe.remove(onid, user, safe);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("le document $file n'est plus récupérable")
    public void getDeletedFile(String file) throws IOException, NoSuchAlgorithmException {
        try {
            mySafe.getFile(file);
        } catch (FileNotFoundException e) {
            assertNotNull(e);
        }
    }

}
