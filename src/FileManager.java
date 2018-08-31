import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    public static int createFile(String filePath) {
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public static int deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete())
                return -1;
        }
        return 0;
    }

    public static int createNewFile(String filePath) {
        int i = deleteFile(filePath);
        return i + createFile(filePath);
    }

    public static int deleteAllFilesInFolder(String folderPath) {
        File directory = new File(folderPath);
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public static int copyFile(String initialFilePath, String finalFilePath) {
        File oldFile = new File(initialFilePath);
        File newFile = new File(finalFilePath);
        try {
            FileUtils.copyFile(oldFile, newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public static int archiveFolderContent(String folderPath) {
        File folder = new File(folderPath);
        if (folder.list().length != 0) {
            DateFormat dateFormat = new SimpleDateFormat("-yyyyMMdd-HHmmss");
            Date date = new Date();
            String[] splittedPath = folderPath.split("\\\\");
            String oldFolderName = splittedPath[splittedPath.length - 1];
            String newFolderName = oldFolderName + dateFormat.format(date);
            String archiveDesinationPath = "D:\\Pastime\\files\\archive\\" + newFolderName;
            File archiveDestination = new File(archiveDesinationPath);
            try {
                FileUtils.copyDirectory(folder, archiveDestination);
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    public static int createDirectory(String directory) {
        File newDirectory = new File(directory);
        if (!newDirectory.exists()) {
            try {
                newDirectory.mkdir();
            } catch (SecurityException se) {
                return -1;
            }
        }
        return 0;
    }

    public static int clearTmp(){
        return deleteAllFilesInFolder("D:\\Pastime\\files\\tmp");
    }
}
