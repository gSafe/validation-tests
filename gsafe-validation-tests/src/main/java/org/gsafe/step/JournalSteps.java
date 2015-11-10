package org.gsafe.step;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.gsafe.mock.ResponseDTO;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class JournalSteps extends GsafeSteps {
    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When("$user récupère les informations du journal concernant le document identifié $onid dans le coffre $safe")
    public void log(String user, String onid, String safe) throws IOException {
        try {
            response = mySafe.getLog(onid, user, safe, null, null, null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When("$user récupère les informations du journal concernant le coffre $safe entre le $date1 et le $date2")
    public void getLogWithDate(String user, String safe, String date1, String date2) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            response = mySafe.getLog(null, user, safe, formatter.parse(date1), formatter.parse(date2), null, null, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When("$user récupère les informations du journal concernant le coffre $safe pour les id entre $id1 et $id2")
    public void getLogWithId(String user, String safe, String id1, String id2) throws IOException {
        try {
            response = mySafe.getLog(null, user, safe, null, null, id1, id2, null);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("$count opérations ont été effectuées sur le fichier")
    public void countObjectsInLog(int count) {
        assertEquals(count, response.log.size());
    }

    @Then("le coffre retourne un ID_CCFN égal à $idccfn")
    public void hasIDCCFN(String idccfn) {
        Assert.assertEquals(idccfn, response.idCCFN);
    }

    @Then("une date et heure de fin de réalisation récente pour chaque opération")
    public void hasDoneDateForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertTrue(response.doneDate.before(new Date()));
            Assert.assertTrue(new Date().getTime() - response.doneDate.getTime() <= 5 * 60 * 1000);
        }
    }

    @Then("un ID_CCFN égal à $idccfn pour chaque opération")
    public void hasIDCCFNForAll(String idccfn) {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertEquals(idccfn, responseDTO.idCCFN);
        }
    }

    @Then("un ID_UTI pour chaque opération")
    public void hasIDUTIForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertNotNull(responseDTO.idUTI);
        }
    }

    @Then("un IDU pour chaque opération")
    public void hasObjectForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertNotNull(responseDTO.idU);
        }
    }

    @Then("une taille pour chaque opération")
    public void hasSizeForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertTrue(responseDTO.size > 0);
        }
    }

    @Then("un algorithme d'empreinte pour chaque opération")
    public void hasAlgoForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertNotNull(responseDTO.algo);
        }
    }

    @Then("une empreinte pour chaque opération")
    public void hasHashForAll() {
        for (ResponseDTO responseDTO : response.log) {
            Assert.assertNotNull(response.hash);
        }
    }
}
