package org.gsafe.step;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class ListerSteps extends GsafeSteps {
    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When("$user récupère la liste des objets numériques du coffre $safe")
    public void list(String user, String safe) throws IOException {
        try {
            response = mySafe.getList(null, user, safe, null, null, null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When(value = "$user récupère la liste des objets numériques identifiés $onid du coffre $safe", priority = 1)
    public void getListById(String user, String onid, String safe) throws IOException {
        try {
            response = mySafe.getList(onid, user, safe, null, null, null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When(value = "$user récupère la liste des objets numériques du coffre $safe déposés entre le $date1 et le $date2", priority = 1)
    public void getLogWithDate(String user, String safe, String date1, String date2) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            response = mySafe.getList(null, user, safe, formatter.parse(date1), formatter.parse(date2), null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When(value = "$user récupère la liste des objets numériques du coffre $safe identifiés entre $id1 et $id2", priority = 1)
    public void getLogWithId(String user, String safe, String id1, String id2) throws IOException {
        try {
            response = mySafe.getList(null, user, safe, null, null, id1, id2, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("$count identifiants sont présents dans la liste")
    public void countObjectsInList(int count) {
        assertEquals(count, response.count);
    }
}
