package Model;

public class FileModel {
    private String nameFile;
    private String extFile;
    private byte[] data;
    private long fileSize;

    public FileModel(String nameFile, String extFile, byte[] data, long size) {
        this.nameFile = nameFile;
        this.extFile = extFile;
        this.data = data;
        this.fileSize = size;
    }
    
    public FileModel(){
        
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getExtFile() {
        return extFile;
    }

    public void setExtFile(String extFile) {
        this.extFile = extFile;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    
    
}
