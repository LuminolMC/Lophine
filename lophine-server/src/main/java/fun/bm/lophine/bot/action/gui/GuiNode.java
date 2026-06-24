package fun.bm.lophine.bot.action.gui;

public class GuiNode {
    protected final String name;
    protected final String description;

    public GuiNode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
