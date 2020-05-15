import java.io.File;

public class CLICore {
    private FilesLoader loader;
    private final CLITokenizer tokenizer;
    private boolean suppressOutput = false;
    private boolean keepRunning = true;

    /**
     * Default constructor used to initialize normally from main, works by taking input from stdin
     */
    public CLICore() {
        this.tokenizer = new CLITokenizer();
    }

    /**
     * Constructor for testing, works by reading the arguments instead of reading from stdin
     * @param args
     */
    public CLICore(String args){
        this.tokenizer = new CLITokenizer(args);
        suppressOutput = true;
    }

    /**
     * initializes the present working directory and format
     */
    public void initCLI(){
        if(!suppressOutput)
        System.out.print("Enter the directory to scan : ");
        String dir = this.tokenizer.readLine();
        if(!suppressOutput)
        System.out.print("Enter specfic formats to scan if any. leave blank for all formats : ");
        String format = this.tokenizer.readLine();
        this.loader = new FilesLoader(new File(dir),format);
        if(suppressOutput)
            this.loader.setSuppressOutput(true);
        this.loader.loadFilesInDirectory();
        fileProcedure();
    }

    private void fileProcedure(){
        if(!suppressOutput)
            System.out.println("Enter the index of operation you want to perform :\n" +
                    "1 - Preview files in the directory\n" +
                    "2 - Rename a specific file with new name\n" +
                    "Usage : 2 <old file name> <new file name>\n" +
                    "3 - Rename all files with a new name (a number from 0,1 … will be added)\n" +
                    "Usage : 3 <new file name>\n" +
                    "4 - Find if a specific file exists\n" +
                    "Usage : 4 <file name>\n" +
                    "5 - Sort files by name\n" +
                    "6 - Delete a file\n" +
                    "Usage : 6 <file name>\n" +
                    "7 - Exit");
        while(keepRunning) {
            Operation op = tokenizer.getNextOperation();
            if (op != null) {
                performOperation(op);
            }
        }
    }

    /**
     * switch the op code and perform operations based on them
     * @param op
     */
    private void performOperation(Operation op){
        switch (op.getIndex()){
            case 1 : loader.preview();break;
            case 2 : {
                    if(validateArguments(2,op.getArguments())){
                        loader.rename(op.getArguments()[0],op.getArguments()[1]);
                    }
                }
                break;
            case 3 : {
                    if(validateArguments(1,op.getArguments())){
                        loader.rename(op.getArguments()[0]);
                    }
                }
                break;
            case 4 : {
                    if(validateArguments(1,op.getArguments())) {
                        loader.find(op.getArguments()[0]);
                    }
                }
                break;
            case 5 :
                loader.sortByName();
                break;
            case 6 : {
                    if(validateArguments(1,op.getArguments())){
                        loader.delete(op.getArguments()[0]);
                    }
                }
                break;
            case 7 : {
                if(!suppressOutput)
                System.out.println("bye!");
                keepRunning = false;
            }
        }
    }

    /**
     * checks if array has the required number of elements
     * @param num
     * @param arr
     * @return true if array has the required number of elements else false
     */
    private boolean validateArguments(int num,String[] arr){
        if(arr.length != num){
            if(!suppressOutput)
            System.out.println("Check the number of arguments!");
            return false;
        }
        return true;
    }

}
