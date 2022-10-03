import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String mainPath = "C:\\Users\\USER\\IdeaProjects\\Install\\Install\\Games\\";


    public static void main(String[] args) {
        StringBuilder tmp = new StringBuilder();

        List<String> pathDirs = Arrays.asList(
                "src", "res", "savegames", "temp",
                "src\\main", "src\\test",
                "res\\drawables", "res\\vectors", "res\\icons"
        );
        List<String> pathFiles = Arrays.asList(
                "src\\main\\Main.java", "src\\main\\Utils.java",
                "temp\\temp.txt"
        );


        for (String createDir : pathDirs){
            File dir = new File((mainPath + createDir));
            if (dir.mkdir()) {
                System.out.println("Папка " + createDir + " успешно создана");
                writeLog(tmp, dir, true);
            }
        }

        for (String createFile : pathFiles) {
            File file = new File(mainPath, createFile);
            try {
                if (file.createNewFile()){
                    System.out.println("Файл " + createFile + " успешно создан");
                    writeLog(tmp, file, true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mainPath + "temp/temp.txt"))) {
            String text = tmp.toString();
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void writeLog(StringBuilder tmp, File file, boolean b) {
        tmp
                .append("\n")
                .append(b ? "" : "Ошибка! ")
                .append(file.isDirectory() ? "Директория " : "Файл: ")
                .append(file.getAbsolutePath())
                .append(b ? " - создан" : " - не создан");
    }

}