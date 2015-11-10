package org.gsafe.step;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.gsafe.mock.Container;
import org.gsafe.mock.Safe;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class LireSteps extends GsafeSteps {
    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    @Given("$user a un coffre $safe avec le conteneur $container et le fichier $file identifié $fileid")
    public void givenSafeWithContainerAndFileId(String user, String safe, String container, String file, String fileid) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        posted = new File("src/main/resources/" + file);
        mySafe.store(new FileInputStream(posted));
    }

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------
    @When(value = "$user lit le document identifié $onid dans le conteneur $conteneur du coffre $safe", priority = 1)
    public void read(String user, String onid, String conteneur, String safe) throws IOException {
        try {
            response = mySafe.read(onid, user, safe, conteneur);
            stored = response.file;
        } catch (Exception e) {
            receivedException = e;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("$user possède une copie du fichier $file avec l'empreinte $algorithme $empreinte")
    public void validateHash(String user, String file, String algorithme, String empreinte) throws IOException, NoSuchAlgorithmException {
        assertEquals(empreinte, Container.getDigestString(new FileInputStream(stored), algorithme));
    }

    @Then("le fichier récupéré est le même que $file")
    public void thenFilesAreEqual(String file) throws IOException {
        assertTrue(FileUtils.contentEquals(stored, posted));
    }
}
