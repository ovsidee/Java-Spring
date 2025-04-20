package pl.edu.pja.tpo07.model;

import java.io.Serializable;

public class CodeToFormat implements Serializable {
    public String id;
    public String beforeFormatCode;
    public String formattedCode;
    public Long expirationTime;

    public String getId() { return id; }
    public String getFormattedCode() { return formattedCode; }
    public String getBeforeFormatCode() { return beforeFormatCode; }
    public Long getExpirationTime() { return expirationTime; }

    public void setId(String id) { this.id = id; }
    public void setBeforeFormatCode(String beforeFormatCode) { this.beforeFormatCode = beforeFormatCode; }
    public void setFormattedCode(String afterFormattingCode) { this.formattedCode = afterFormattingCode; }
    public void setExpirationTime(Long expirationTime) { this.expirationTime = expirationTime; }
}