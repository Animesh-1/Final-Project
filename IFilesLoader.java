public interface IFilesLoader {
    /**
     * used to load the files of a directory
     */
    void loadFilesInDirectory();

    /**
     * Used to sort the files by names
     */
    void sortByName();

    /**
     * used to rename a file
     * @param filename
     * @param newName
     */
    void rename(String filename,String newName);

    /**
     * used to rename all the files
     * @param newName
     */
    void rename(String newName);

    /**
     * used to find a specific file in the list
     * @param filename
     */
    void find(String filename);

    /**
     * used to see the present file list
     */
    void preview();

    /**
     * used to delete a file
     */
    void delete(String filename);
}
