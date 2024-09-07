package duck.commands;

import duck.data.TaskList;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command that handles invalid instructions given by the user.
 * When executed, it informs the user that the instruction is not recognized.
 */
public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "Hey, that's not a valid instruction! I don't know how to respond to that.\n";

    /**
     * Constructs an InvalidCommand with the specified message.
     *
     * @param message The unrecognized command or instruction provided by the user.
     */
    public InvalidCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by notifying the user that the instruction is invalid.
     * This method prints a message to the console.
     *
     * @param tasks The list of tasks (not used in this command).
     * @param storage The storage system (not used in this command).
     * @param ui The user interface (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the InvalidCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
