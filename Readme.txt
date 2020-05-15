Animesh Srivastava, 824304553, May 14th 2020.

The main aim of this program is to help mass rename files with numbers. It can also be used as a quick file manager.
This application can rename files either single file or many files, display a list of them, search for a file in the given directory and delete the file.


Summary :

IFilesLoader : Its an interface that is implemented by the AbstractFileManager class. It is used to provide methods for file manager

AbstractFileManager : It is an abstract class that implements IFilesLoader and is used as an abstract file manager

FilesLoader : This is the main file loading implementation class. It is a subclass of AbstractFileManager. This class is responsible for performing all the file operations
Operation : This is a simple structure class used to pass operations from CLITokenizer to CLICore.

CLITokenizer : This class is responsible for reading console input, parsing and creating an Operation object that is passed to CLICore for further processing.

CLICore : This class is used to interact with the user. It is also responsible to create and pass operations to the FilesLoader object which then performs the operations.

Main : This class is used to instantiate the CLICore and start the application