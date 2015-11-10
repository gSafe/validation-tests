package org.gsafe.mock;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResponseBuilder {

    private ResponseDTO responseDTO;

    public ResponseBuilder() {
        responseDTO = new ResponseDTO();
    }

    public ResponseBuilder(ResponseDTO responseDTO) {
        this.responseDTO = responseDTO;
    }

    public ResponseBuilder example() throws ParseException {
        responseDTO.algo = "SHA256";
        responseDTO.hash = "azrda39a3ee5e6b4bd3255bfeqf95601890afd879";
        responseDTO.doneDate = new Date();
        responseDTO.idCCFN = "coffre_456";
        responseDTO.idU = "1";
        responseDTO.conteneur = "documents_perso";
        responseDTO.idUTI = "Leon Martin";
        responseDTO.size = 100;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        responseDTO.depot = format.parse("12/10/2014 00:00");
        return this;
    }

    public ResponseBuilder object(String object) {
        responseDTO.idU = object;
        return this;
    }

    public ResponseBuilder coffre(String coffre) {
        responseDTO.idCCFN = coffre;
        return this;
    }

    public ResponseBuilder conteneur(String conteneur) {
        responseDTO.conteneur = conteneur;
        return this;
    }

    public ResponseBuilder user(String user) {
        responseDTO.idUTI = user;
        return this;
    }

    public ResponseBuilder depot(Date depot) {
        responseDTO.depot = depot;
        return this;
    }

    public ResponseBuilder done(Date done) {
        responseDTO.doneDate = done;
        return this;
    }

    public ResponseBuilder size(int size) {
        responseDTO.size = size;
        return this;
    }

    public ResponseBuilder algo(String algo) {
        responseDTO.algo = algo;
        return this;
    }

    public ResponseBuilder hash(String hash) {
        responseDTO.hash = hash;
        return this;
    }

    public ResponseBuilder file(File file) {
        responseDTO.file = file;
        return this;
    }

    public ResponseBuilder list(List<String> list) {
        responseDTO.list = list;
        responseDTO.count = list.size();
        return this;
    }

    public ResponseBuilder log(List<ResponseDTO> log) {
        responseDTO.log = log;
        return this;
    }

    public ResponseDTO build() {
        return responseDTO;
    }
}
