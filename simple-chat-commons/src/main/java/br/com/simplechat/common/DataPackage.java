package br.com.simplechat.common;

import java.io.Serializable;

public class DataPackage implements Serializable{

    private static final long serialVersionUID = 1L;

    private PackageHeader header;
    private String content;

    public DataPackage(PackageHeader header, String content) {
        this.header = header;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PackageHeader getHeader() {
        return header;
    }

    public void setHeader(PackageHeader header) {
        this.header = header;
    }


}
