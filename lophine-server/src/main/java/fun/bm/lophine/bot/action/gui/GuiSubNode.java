package fun.bm.lophine.bot.action.gui;

import java.util.Set;

public class GuiSubNode extends GuiRootNode {
    protected final GuiNode parent;

    public GuiSubNode(String name, String description, GuiNode parent, Set<GuiNode> children) {
        super(name, description, children);
        this.parent = parent;
    }

    public GuiSubNode(String name, String description, GuiNode parent) {
        super(name, description);
        this.parent = parent;
    }

    public GuiNode getParent() {
        return parent;
    }
}
