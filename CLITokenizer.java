import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

public class CLITokenizer {
    private BufferedReader reader;

    /**
     * default constructor called from CLICore works by reading from stdin
     */
    public CLITokenizer() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Constructor primarily for testing, works by reading from args
     * @param args
     */
    public CLITokenizer(String args){
        this.reader = new BufferedReader(new StringReader(args));
    }

    /**
     * reads the next line
     * @return string containing the next line
     */
    public String readLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * reads the line, parses it
     * @return the parsed operation object, null if invalid operation
     */
    public Operation getNextOperation(){
        String line = readLine();
        if(line.length()<=0){
            System.out.println("Invalid operation");
            return null;
        }
        String[] tokens = line.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(tokens[0]);
        }catch (NumberFormatException e){
            System.out.println("Invalid operation");
            return null;
        }
        if(index < 0 || index > 7){
            System.out.println("Invalid operation");
            return null;
        }
        return new Operation(index, Arrays.copyOfRange(tokens, 1, tokens.length));
    }
}
