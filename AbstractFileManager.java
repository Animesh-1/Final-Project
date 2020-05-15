/**
 * Abstract generic class. as T can be a file string or any custom object containing directory
 * @param <T>
 */
public abstract class AbstractFileManager<T> implements IFilesLoader{
    protected T directory; // holds the present working directory
    protected String format; // holds the present working format. "" if all files are accepted.

    public T getDirectory() { return directory; }

    public void setDirectory(T directory) {
        this.directory = directory;
    }
    public String getFormat() {
        return format;
    }
}
