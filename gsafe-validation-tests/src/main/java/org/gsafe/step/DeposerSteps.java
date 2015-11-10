package org.gsafe.step;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.gsafe.mock.Safe;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeposerSteps extends GsafeSteps {

    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------

    @When("$user dépose le document $path dans le conteneur $container du coffre $safe")
    public void store(String user, String path, String container, String safe) {
        posted = new File("src/main/resources/" + path);
        try {
            mySafe.getContainer(user, container).store(new FileInputStream(posted));
        } catch (IOException e) {
            receivedException = e;
        }
    }

    @When("$user dépose un document vide dans le conteneur $container du coffre $safe")
    public void storeEmptyDoc(String user, String container, String safe) {
        try {
            mySafe.getContainer(user, container).store(null);
        } catch (IOException e) {
            receivedException = e;
        }
    }

    @When(value = "$user dépose le document $path avec l'empreinte $algorithme $empreinte dans le conteneur $container du coffre $safe", priority = 1)
    public void storeWithControl(String user, String path, String empreinte, String algorithm, String container, String safe) throws IOException, NoSuchAlgorithmException {
        posted = new File("src/main/resources/" + path);
        try {
            mySafe.getContainer(user, container).store(new FileInputStream(posted), empreinte, algorithm);
        } catch (Exception e) {
            receivedException = e;
        }
    }

    @When("$user dépose le document $path dans le coffre $safe")
    public void store(String user, String path, String safe) throws IOException {
        posted = new File("src/main/resources/" + path);
        mySafe.store(new FileInputStream(posted));
    }

    @When("$user dépose un document vide dans le coffre $safe")
    public void store(String user, String safe) {
        try {
            mySafe.store((InputStream) null);
        } catch (IOException e) {
            receivedException = e;
        }
    }

    @When("$user récupère le document $path depuis le conteneur $container du coffre $safe")
    public void dl(String user, String path, String container, String safe) {
        try {
            response = mySafe.getContainer(user, container).getFile(path);
            stored = response.file;
        } catch (IOException e) {
            receivedException = e;
        }
    }

    @When("$user récupère le document $path depuis le coffre $safe")
    public void dl(String user, String path, String safe) throws IOException {
        response = mySafe.getFile(path);
        stored = response.file;
    }

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("le fichier récupéré est le même que celui qui avait été déposé")
    public void thenFilesAreEqual() throws IOException {
        assertTrue(FileUtils.contentEquals(stored, posted));
    }

    @Then("le dépôt est refusé")
    @Alias("l'accès est refusé")
    public void thenError() throws IOException {
        assertNotNull(receivedException);
    }
}
