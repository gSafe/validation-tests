package org.gsafe.mock;

import java.io.File;
import java.util.Date;
import java.util.List;

public class ResponseDTO {

    public String idU;
    public String idCCFN;
    public String idUTI;
    public Date doneDate;
    public int size;
    public String algo;
    public String hash;
    public File file;
    public int count;
    public List<String> list;
    public String conteneur;
    public Date depot;
    public List<ResponseDTO> log;
}
