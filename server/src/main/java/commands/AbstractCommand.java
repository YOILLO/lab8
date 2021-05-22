package commands;

/**
 * Abstract command, to print name and description
 */
public abstract class AbstractCommand implements commands.ICommand {
    private String name;
    private String description;


    public AbstractCommand(String nm, String descr) {
        name = nm;
        description = descr;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " " + description;
    };

    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}
