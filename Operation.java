public class Operation {
    private int index;
    private String[] arguments;

    public Operation(int index, String[] arguments) {
        this.index = index;
        this.arguments = arguments;
    }

    public int getIndex() {
        return index;
    }

    public String[] getArguments() {
        return arguments;
    }
}
