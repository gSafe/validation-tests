package org.gsafe.step;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.gsafe.mock.ResponseBuilder;
import org.gsafe.mock.ResponseDTO;
import org.gsafe.mock.Safe;
import org.jbehave.core.annotations.*;

public class CompterSteps extends GsafeSteps {

    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------

    @When(value = "$user compte les objets numériques du coffre $safe déposés entre le $date1 et le $date2", priority = 1)
    public void compterAvecDate(String user, String safe, String date1, String date2) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            response = mySafe.count(user, safe, formatter.parse(date1), formatter.parse(date2), null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When(value = "$user compte les objets numériques du coffre $safe possédant des identifiants compris entre $id1 et $id2", priority = 1)
    public void compterAvecId(String user, String safe, String id1, String id2) throws IOException {
        try {
            response = mySafe.count(user, safe, null, null, id1, id2, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When("$user compte les objets numériques du coffre $safe")
    public void compter(String user, String safe) throws IOException {
        try {
            response = mySafe.count(user, safe, null, null, null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("il y a $count objets numériques correspondants")
    public void countObjectsInList(int count) {
        assertEquals(count, response.count);
    }

}
