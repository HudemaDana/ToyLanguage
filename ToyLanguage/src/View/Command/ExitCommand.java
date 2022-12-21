package View.Command;

public class ExitCommand extends ACommand{
    public ExitCommand(String key, String description){
        super(key, description);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
