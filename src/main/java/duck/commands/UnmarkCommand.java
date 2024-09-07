package duck.commands;

import duck.common.Utils;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;


/**
 * Represents a command to unmark a task as incomplete.
 * This command is used to indicate that a task is no longer completed and should be marked as incomplete.
 */
public class UnmarkCommand extends Command {

    private static final String UNMARK_COMMAND_ERROR_MESSAGE = """
            Update tasks with correct format please >:(
            mark/unmark {index of task to update}
            """;

    /**
     * Constructs an UnmarkCommand with the specified message.
     *
     * @param message The message associated with the command.
     */
    public UnmarkCommand(String message) {
        super(message);
    }

    /**
     * Executes the unmark command, marking the specified task as incomplete.
     * It verifies the correctness of the command format and updates the task list and storage accordingly.
     *
     * @param tasks   The list of tasks to be manipulated by the command.
     * @param storage The storage system for saving and loading tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @throws DuckException If an error occurs during the execution of the command,
     *                       such as an invalid task index or incorrect command format.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        if (!Utils.isCorrectUpdateFormat(message)) {
            throw new DuckException(UNMARK_COMMAND_ERROR_MESSAGE);
        }

        int taskIndex = Utils.getTaskIndex(message);
        tasks.updateTaskStatus(taskIndex, false);
        storage.writeTasks(tasks);
    }

    /**
     * Indicates whether this command signifies an exit operation.
     *
     * @return false, as unmarking a task does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
