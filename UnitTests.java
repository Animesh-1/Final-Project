import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UnitTests {

    public static void main(String[] args) throws IOException {
        // since the application is a file manager I need a folder to create and delete files
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Make sure there is no directory named 'test' from where you are running this");
        System.out.println("Press enter to continue");
        reader.readLine();
        new File("test").mkdir();
        //tests begin from here
        sortTest();
        deleteTest();
        renameTest();
        renameAllTest();
        randomDirTest();
        operationTest();
    }

    /**
     * creates a file called test1 and deletes it
     * @throws IOException
     */
    public static void deleteTest() throws IOException {
        new File("test/test1.txt").createNewFile();
        CLICore core = new CLICore("test\n\n6 test1.txt\n7");
        core.initCLI();
        if(new File("test/test1.txt").exists()){
            System.out.println("Delete test FAIL");
        }else {
            System.out.println("Delete test PASS!");
        }
    }

    /**
     * renames a file called test1 to test5 and check if that was successful.
     * @throws IOException
     */
    public static void renameTest() throws IOException {
        new File("test/test1.txt").createNewFile();
        CLICore core = new CLICore("test\n\n2 test1.txt test5.txt\n7");
        core.initCLI();
        if(new File("test/test5.txt").exists()){
            System.out.println("Rename test PASS!");
        }else {
            System.out.println("Rename test FAIL!");
        }
    }

    /**
     * check if some random directory is entered
     */
    public static void randomDirTest() {
        try {
            CLICore core = new CLICore("ljkshadfjadshfljnsfjdngkjsfn\n\n7");
            core.initCLI();
            System.out.println("Random dir test PASS!");
        }catch (Exception e){
            System.out.println("Random dir test FAIL!");
        }
    }

    /**
     * renames all the files in the directory
     * @throws IOException
     */
    public static void renameAllTest() throws IOException{
        new File("test/test1.txt").createNewFile();
        new File("test/test2.txt").createNewFile();
        CLICore core = new CLICore("test\n\n3 kat.txt\n7");
        core.initCLI();
        if(new File("test/0kat.txt").exists() && new File("test/1kat.txt").exists()){
            System.out.println("Rename all test PASS!");
        }else {
            System.out.println("Rename all test FAIL!");
        }
    }

    /**
     * checks if the sort method in loader is working as expected or not
     * @throws IOException
     */
    public static void sortTest() throws IOException {
        new File("test/t1.txt").createNewFile();
        new File("test/bv1.txt").createNewFile();
        new File("test/0.txt").createNewFile();
        new File("test/test.txt").createNewFile();
        FilesLoader loader = new FilesLoader(new File("test"),"");
        loader.loadFilesInDirectory();
        loader.sortByName();
        List<File> f = loader.getFiles();
        if(f.get(0).getName().equals("0.txt") && f.get(1).getName().equals("bv1.txt") && f.get(2).getName().equals("t1.txt")
        && f.get(3).getName().equals("test.txt")){
            System.out.println("Sort test PASS!");
        }
        else {
            System.out.println("Sort test FAIL!");
        }
    }

    /**
     * checks if the CLITokenizer is tokenizing or not.
     */
    public static void operationTest() {
        CLITokenizer tokenizer = new CLITokenizer("5 test test1");
        Operation op = tokenizer.getNextOperation();
        if(op.getIndex() == 5 && op.getArguments()[0].equals("test") && op.getArguments()[1].equals("test1")){
            System.out.println("Operation test PASS!");
        }else {
            System.out.println("Operation test FAIL!");
        }
    }
}
