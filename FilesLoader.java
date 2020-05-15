import java.io.File;
import java.util.*;

/**
 * Implementation of AbstractFileManager with File Generic
 */
public class FilesLoader extends AbstractFileManager<File> {
    private List<File> files;
    private boolean isSorted;
    private boolean suppressOutput = false;

    /**
     * default constructor
     */
    public FilesLoader() {
        this.files = new ArrayList<>();
        isSorted = false;
    }

    /**
     * initializes the loader with a directory
     * @param dir
     */
    public FilesLoader(File dir){
        this();
        this.directory = dir;
    }

    /**
     * initializes the loader with a directory as well as a working format
     * @param dir
     * @param format
     */
    public FilesLoader(File dir,String format){
        this(dir);
        this.format = format;
    }

    /**
     * loads all the files in the present working directory
     */
    @Override
    public void loadFilesInDirectory() {
        if(this.directory == null){
            if(!suppressOutput)
            System.out.println("Please select a directory first!");
            return;
        }
        try {
            this.files = Arrays.asList(Objects.requireNonNull(this.directory.listFiles()));
        }catch (NullPointerException e){
            if(!suppressOutput)
            System.out.println("Directory does not exist!");
            return;
        }
        if(!format.equals("")){
            List<File> formatArr = new ArrayList<>();
            for(File f:files){
                if(getFormat(f.getName()).equalsIgnoreCase(this.format)){
                    formatArr.add(f);
                }
            }
            this.files = formatArr;
        }
    }

    /**
     * takes a filename and returns its extension
     * @param filename
     * @return filename extension
     */
    private String getFormat(String filename){
        String format = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            format = filename.substring(i+1);
        }
        return format;
    }

    /**
     * sorts the array list of files by name
     */
    @Override
    public void sortByName() {
        int size = this.files.size();
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for (int j = i+1; j < size; j++)
                if (this.files.get(j).compareTo(this.files.get(min)) < 0)
                    min = j;
            File temp = this.files.get(min);
            this.files.set(min,this.files.get(i));
            this.files.set(i,temp);
        }
        isSorted = true;
    }

    /**
     * renames a single filename file into newName file
     * @param filename
     * @param newName
     */
    @Override
    public void rename(String filename, String newName) {
        int index = binSearch(filename);
        if(index == -1){
            if(!suppressOutput)
            System.out.println("Can't rename! "+filename+" does not exist");
        }
        else {
            if(!files.get(index).exists()){
                if(!suppressOutput)
                System.out.println("Can't rename! "+filename+" does not exist");
            }
            else if(new File(newName).exists()){
                if(!suppressOutput)
                System.out.println("Can't rename! The file with "+newName+" already exists");
            }
            else {
                if(files.get(index).renameTo(new File(directory+"/"+newName))){
                    if(!suppressOutput)
                    System.out.println("Renamed successfully");
                    loadFilesInDirectory();
                }
                else {
                    if(!suppressOutput)
                    System.out.println("Rename failed");
                }
                isSorted = false;
            }
        }
    }

    /**
     * renames all the files in the present working directory to #newName
     * where # is the counter starting from 0...
     * @param newName
     */
    @Override
    public void rename(String newName) {
        int counter = 0;
        for(File f:files){
            rename(f.getName(),counter+""+newName);
            counter++;
        }
        loadFilesInDirectory();
    }

    private int binSearch(String filename){
        if(!isSorted)
            sortByName();
        int left = 0;
        int right = this.files.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = filename.compareTo(this.files.get(mid).getName());
            if (compare == 0)
                return mid;
            if (compare > 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    @Override
    public void find(String filename) {
        int index = binSearch(filename);
        if (index==-1){
            if(!suppressOutput)
            System.out.println("File does not exist");
        }else {
            if(!suppressOutput)
            System.out.println("File exists");
        }
    }

    /**
     * shows the files in the present working directory
     */
    @Override
    public void preview() {
        int count = 0;
        if(!suppressOutput)
        System.out.println("In Directory : "+directory.getAbsolutePath());
        if(!this.format.equalsIgnoreCase("")){
            if(!suppressOutput)
            System.out.println("Working format : "+this.format);
        }
        for(File i : files){
            count++;
            if(!suppressOutput)
            System.out.println(i.getName()+" - "+i.getTotalSpace()+" Bytes");
        }
        if(!suppressOutput)
        System.out.println("Total "+count+" files");
    }

    /**
     * deletes the given file
     * @param filename
     */
    @Override
    public void delete(String filename) {
        int index = binSearch(filename);
        if(index == -1){
            if(!suppressOutput)
            System.out.println("Can't delete! "+filename+" does not exist");
        }
        else {
            if(this.files.get(index).delete()){
                if(!suppressOutput)
                System.out.println("Deleted the file successfully");
                loadFilesInDirectory();
            }
            else {
                if(!suppressOutput)
                System.out.println("Deletion failed!");
            }
        }
    }

    public void setSuppressOutput(boolean suppressOutput) {
        this.suppressOutput = suppressOutput;
    }

    public List<File> getFiles() {
        return files;
    }
}
