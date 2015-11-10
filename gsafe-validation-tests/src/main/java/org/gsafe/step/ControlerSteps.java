package org.gsafe.step;

import org.gsafe.mock.Safe;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

public class ControlerSteps extends GsafeSteps {

    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    @Given("$user a un coffre $safe avec le conteneur $container et le fichier $file déposé le $date")
    public void givenSafeWithContainerAndFileWithDate(String user, String safe, String container, String file, String date) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        posted = new File("src/main/resources/" + file);
        mySafe.store(new FileInputStream(posted));
    }

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When("$user contrôle le document altéré identifié $onid dans le coffre $safe")
    public void checkAlteration(String user, String onid, String safe) throws IOException {
        try {
            response = mySafe.check(onid, user, safe);
            response.hash += "42";
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When("$user contrôle le document identifié $onid dans le coffre $safe")
    public void check(String user, String onid, String safe) throws IOException {
        try {
            response = mySafe.check(onid, user, safe);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------
    @Then("il récupère les données du contrôle de l'objet $onid avec l'empreinte $algorithme $empreinte")
    public void validateCheck(String onid, String algorithme, String empreinte) {
        Assert.assertEquals(onid, response.idU);
        Assert.assertEquals(empreinte, response.hash);
    }

    @Then("il récupère les données du contrôle de l'objet $onid avec une empreinte différente de l'empreinte $algorithme $empreinte")
    public void validateCheckAlteration(String onid, String algorithme, String empreinte) {
        Assert.assertNotSame(onid, response.idU);
        Assert.assertNotSame(empreinte, response.hash);
    }
}
