package org.gsafe.step;

import org.gsafe.mock.ResponseBuilder;
import org.gsafe.mock.ResponseDTO;
import org.gsafe.mock.Safe;
import org.jbehave.core.annotations.*;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class GsafeSteps {

    protected Safe mySafe;

    protected File stored, posted;

    protected Exception receivedException = null;

    protected ResponseDTO response;
    protected ResponseBuilder responseBuilder;

    @BeforeStory
    public void beforeStory() throws IOException {
        responseBuilder = new ResponseBuilder();
    }

    @AfterStory
    public void afterStory() {
        if (stored != null)
            stored.delete();
    }

    @BeforeScenario
    public void beforeScenario() throws IOException {
    }

    @AfterScenario
    public void afterScenario() {
    }

    // ---------------------------------------------------------------------------------------------
    // GIVEN
    // ---------------------------------------------------------------------------------------------

    @Given(value = "$user a un coffre $safe ayant le conteneur $container et le fichier $file sur lequel $readop opérations de lecture et $checkop opérations de controle ont été effectuées")
    public void givenSafeWithContainerAndFileAndOperations(String user, String safe, String container, String file, int readop, int checkop) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        posted = new File("src/main/resources/" + file);
        mySafe.store(new FileInputStream(posted));
        for (int i = 0; i < readop; i++) {
            mySafe.logOperation("read");
        }
        for (int i = 0; i < checkop; i++) {
            mySafe.logOperation("check");
        }
    }

    @Given(value = "$user a un coffre $safe avec le conteneur $container comprenant $nbObjects objets numériques", priority = 1)
    public void givenSafeWithContainerAndFiles(String user, String safe, String container, int nbObjects) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        for (int i = 0; i < nbObjects; i++) {
            mySafe.store(String.valueOf(i));
        }
    }

    @Given("$user a un coffre $safe avec le conteneur $container et le fichier $file déposé le $date")
    public void givenSafeWithContainerAndFileWithDate(String user, String safe, String container, String file, String date) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        posted = new File("src/main/resources/" + file);
        mySafe.store(new FileInputStream(posted));
    }

    @Given("$user a un coffre $safe avec le conteneur $container et le fichier $file")
    public void givenSafeWithContainerAndFile(String user, String safe, String container, String file) throws IOException, ParseException {
        mySafe = new Safe(safe, container, user);
        posted = new File("src/main/resources/" + file);
        mySafe.store(new FileInputStream(posted));
    }

    @Given("$user a un coffre $safe avec le conteneur $container")
    public void givenSafeWithContainer(String user, String safe, String container) throws ParseException {
        mySafe = new Safe(safe, container, user);
    }

    @Given("un coffre $safe sans conteneur appartenant à $user")
    public void givenSafe(String safe, String user) throws ParseException {
        mySafe = new Safe(safe, null, user);
    }

    // ---------------------------------------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------------------------------------

    @Then("le dépôt est refusé")
    @Aliases(values = { "l'accès est refusé", "la destruction est refusée", "le document n'est pas trouvé" })
    public void thenError() throws IOException {
        assertNotNull(receivedException);
    }

    @Then("le coffre retourne un IDU")
    public void hasIDU() {
        assertNotNull(response.idU);
    }

    @Then("le coffre retourne une liste de $nb IDU")
    public void hasIDU(int nb) {
        assertEquals(nb, response.count);
    }

    @Then("un ID_CCFN égal à $idccfn")
    public void hasIDCCFN(String idccfn) {
        Assert.assertEquals(idccfn, response.idCCFN);
    }

    @Then("un conteneur égal à $conteneur")
    public void hasConteneur(String conteneur) {
        Assert.assertEquals(conteneur, response.conteneur);
    }

    @Then("un ID_UTI égal à $iduti")
    public void hasIDUTI(String iduti) {
        Assert.assertEquals(iduti, response.idUTI);
    }

    @Then("une date et heure de fin de réalisation récente")
    public void hasDate() {
        Assert.assertTrue(response.doneDate.before(new Date()));
        Assert.assertTrue(new Date().getTime() - response.doneDate.getTime() <= 5 * 60 * 1000);
    }

    @Then("une date et heure de dépot égale à $date")
    public void hasDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Assert.assertTrue(response.depot.equals(format.parse(date)));
    }

    @Then("une taille égale à la taille de $file")
    public void hasSize(String file) {
        Assert.assertTrue(response.size == 100);
    }

    @Then("un algorithme d'empreinte égal à $algo")
    public void hasAlgo(String algo) {
        Assert.assertEquals(algo, response.algo);
    }

    @Then("une empreinte égale à $hash")
    public void hasHash(String hash) {
        Assert.assertEquals(hash, response.hash);
    }

    @Then("une empreinte différente de $hash")
    public void hasntHash(String hash) {
        Assert.assertNotSame(hash, response.hash);
    }
}
