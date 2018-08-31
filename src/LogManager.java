import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager {

    public static int clearLogPart(){
        FileManager.archiveFolderContent("D:\\Pastime\\files\\log");
        return FileManager.deleteAllFilesInFolder("D:\\Pastime\\files\\log");
    }

    public static int createLogDirectory(){
        return FileManager.createDirectory("D:\\Pastime\\files\\log");
    }

    public static int createLogFile(){
        return FileManager.createFile("D:\\Pastime\\files\\log\\log.txt");
    }

    public static int writeLog(String message){
        File logFile = new File("D:\\Pastime\\files\\log\\log.txt");
        try {
            FileWriter writer = new FileWriter(logFile, true);
            try{
                writer.write(message);
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public static int init(){
        clearLogPart();
        createLogDirectory();
        createLogFile();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        Date date = new Date();
        return writeLog("Started at : "+dateFormat.format(date)+"\n");
    }

    public static int end(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        Date date = new Date();
        return writeLog("Ended at : "+dateFormat.format(date));
    }
}
