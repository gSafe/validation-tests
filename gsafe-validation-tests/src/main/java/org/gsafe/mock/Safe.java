package org.gsafe.mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.base.Strings;

public class Safe {

    Container container = new Container();

    String safeName;

    String owner;

    List<String> list;
    List<ResponseDTO> log;

    ResponseBuilder builder;

    public Safe(String safe, String container, String user) throws ParseException {
        safeName = safe;
        owner = user;
        this.container.setContainerName(container);
        this.container.safeName = safeName;
        log = new ArrayList<ResponseDTO>();
        list = new ArrayList<String>();
        builder = new ResponseBuilder().example();
        builder.coffre(safeName);
    }

    public Container getContainer(String user, String container) throws IOException {
        if (this.owner == null || !this.owner.equals(user))
            throw new IOException("Access denied");
        if (this.container.getContainerName() == null && container == null || this.container.getContainerName() != null && this.container.getContainerName().equals(container))
            return this.container;
        else
            throw new IOException("Container not found");
    }

    public void store(InputStream inputStream) throws IOException {
        container.store(inputStream);
    }

    public void store(InputStream inputStream, String hash, String algorithm) throws IOException, NoSuchAlgorithmException {
        container.store(inputStream, hash, algorithm);
    }

    public ResponseDTO getFile(String path) throws FileNotFoundException {
        return new ResponseBuilder(container.getFile(path)).coffre(safeName).build();
    }

    public ResponseDTO getFileById(String id) throws FileNotFoundException {
        return getFile(id);
    }

    public ResponseDTO read(String id, String user, String safe, String container) throws IOException {
        if (this.owner == null || !this.owner.equals(user))
            throw new IOException("Access denied");
        return getFileById(id);
    }

    public ResponseDTO remove(String id, String user, String safe) throws IOException {
        if (this.owner == null || !this.owner.equals(user))
            throw new IOException("Access denied");
        return new ResponseBuilder(container.remove()).coffre(safeName).build();

    }

    public ResponseDTO readMeta(String id, String user, String safe, String container) throws IOException {
        if (this.owner == null || !this.owner.equals(user))
            throw new IOException("Access denied");
        return builder.build();
    }

    public ResponseDTO check(String id, String user, String safe) throws IOException {
        if (this.owner == null || !this.owner.equals(user))
            throw new IOException("Access denied");
        return builder.object(id).hash("da39a3ee5e6b4bd3255bfef95601890afd879").build();
    }

    public void logOperation(String op) throws ParseException {
        log.add(builder.example().build());
    }

    public void store(String id) {
        list.add(id);
    }

    public ResponseDTO getLog(String onid, String user, String safe, Date date1, Date date2, String id1, String id2, String container) {
        return builder.log(log).build();
    }

    public ResponseDTO getList(String onid, String user, String safe, Date date1, Date date2, String id1, String id2, String container) {
        List<String> result = new ArrayList<String>();
        if (!Strings.isNullOrEmpty(onid)) {
            result.add(onid);
            return builder.list(result).build();
        } else if (!Strings.isNullOrEmpty(id1) && !Strings.isNullOrEmpty(id2)) {
            int i1 = Integer.valueOf(id1), i2 = Integer.valueOf(id2);
            for (String s : list) {
                int i = Integer.valueOf(s);
                if (i >= i1 && i <= i2) {
                    result.add(s);
                }
            }
            return builder.list(result).build();
        }
        return builder.list(list).build();
    }


    public ResponseDTO count(String user, String safe, Date date1, Date date2, String id1, String id2, String container) {
        List<String> result = new ArrayList<String>();
        if (!Strings.isNullOrEmpty(id1) && !Strings.isNullOrEmpty(id2)) {
            int i1 = Integer.valueOf(id1), i2 = Integer.valueOf(id2);
            for (String s : list) {
                int i = Integer.valueOf(s);
                if (i >= i1 && i <= i2) {
                    result.add(s);
                }
            }
            return builder.list(result).build();
        }
        return builder.list(list).build();
    }
}
