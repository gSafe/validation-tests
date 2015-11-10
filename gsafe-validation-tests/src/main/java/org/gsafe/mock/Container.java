package org.gsafe.mock;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;

public class Container {

    File file;

    String containerName, safeName;

    ResponseBuilder builder;

    public Container() throws ParseException {
        builder = new ResponseBuilder().example();
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void store(InputStream inputStream) throws IOException {
        if (inputStream == null)
            throw new IOException("Empty document received");
        file = File.createTempFile("store", "tmp");
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    public void store(InputStream inputStream, String algorithm, String hash) throws IOException, NoSuchAlgorithmException {
        file = File.createTempFile("store", "tmp");
        FileUtils.copyInputStreamToFile(inputStream, file);
        String hashControl = getDigestString(new FileInputStream(file), algorithm);
        builder.hash(hash);
        if (!hash.equals(hashControl)) {
            System.out.println(hashControl);
            file.delete();
            throw new IOException("Hashes do not match, the file was not saved");
        }
    }

    public ResponseDTO remove() {
        file.delete();
        return builder.file(file).coffre(safeName).build();
    }

    public ResponseDTO getFile(String path) throws FileNotFoundException {
        return builder.file(file).coffre(safeName).build();
    }

    public void close() {
        file.delete();
    }

    public static byte[] getDigest(InputStream in, String algorithm) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        try {
            DigestInputStream dis = new DigestInputStream(in, md);
            byte[] buffer = new byte[2048];
            while (dis.read(buffer) != -1) {
                //
            }
            dis.close();
        } finally {
            in.close();
        }
        return md.digest();
    }

    public static String getDigestString(InputStream in, String algorithm) throws IOException, NoSuchAlgorithmException {
        byte[] digest = getDigest(in, algorithm);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(String.format("%x", digest[i]));
        }
        return sb.toString();
    }
}
