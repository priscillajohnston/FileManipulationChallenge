import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandlingActivity {
    public static void main(String[] args) throws IOException {
        String data = "priscilla's data 676767!!!";
        String fileName1 = "notes.txt";
        String fileName2 = "data.txt";
        String fileName3 = "log.txt";
        debugFileOperation();
        // Your code here

        // a. Create main directory
        File mainDir = new File("JavaFileSystem");
        mainDir.mkdir();

        // b. Create three text files
        File file1 = new File("JavaFileSystem", fileName1);
        file1.createNewFile();
        File file2 = new File("JavaFileSystem", fileName2);
        file2.createNewFile();
        File file3 = new File( "JavaFileSystem", fileName3);
        file3.createNewFile();

        // c. Write messages to files
       // Using Files (java.nio.file)
        try {
            Files.write(Paths.get("JavaFileSystem/" + file1.getName()), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(Paths.get("JavaFileSystem/" + fileName2), "matthew sixseven".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(Paths.get("JavaFileSystem/" + fileName3), "hiiiiiii".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // d. Read and display file contents
        File[] files = mainDir.listFiles();
        for(File file: files ){
            Path filePath = mainDir.toPath().resolve(file.getName());
            byte[] fileBytes = Files.readAllBytes(filePath);
            System.out.println(new String(fileBytes));
        }

        // e. Create backup directory
        File backupDir = new File("Backup");
        backupDir.mkdir();

        // f. Copy contents to backup file
        File backupFile = new File(backupDir.getName(), "backup.txt"); 
        backupFile.createNewFile();
        
      
        File[] Mainfiles = mainDir.listFiles();
        for(File file : Mainfiles){
            Path source = file.toPath();
            Path destination = backupDir.toPath().resolve("backup.txt");
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        }//only a small issue 

        // g. List all files in both directories
        File[] Bfiles = backupDir.listFiles();
        for(File file : Bfiles){
            System.out.println("File " + file.getName());
        }
        
        for(File file : Mainfiles){
            System.out.println("File " + file.getName());
        }

    }

    public static void debugFileOperation(){
        try {
            // Creating a file with an invalid name (forward slash is invalid for file names on many OS)
            File file = new File("main/fileName.txt");
            // Attempting to write to the invalid file
            FileWriter writer = new FileWriter(file);
            writer.write("Will this fail?");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}