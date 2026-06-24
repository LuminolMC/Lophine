package fun.bm.lophine.bot.action.gui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GuiRootNode extends GuiNode {
    protected final Set<GuiNode> children;

    public GuiRootNode(String name, String description) {
        this(name, description, new HashSet<>());
    }

    public GuiRootNode(String name, String description, Set<GuiNode> children) {
        super(name, description);
        this.children = children;
    }

    public final void child(GuiNode... node) {
        this.children.addAll(List.of(node));
    }

    @SafeVarargs
    public final void child(Supplier<? extends GuiNode>... node) {
        this.children.addAll(Stream.of(node).map(Supplier::get).toList());
    }

    public Set<GuiNode> getChildren() {
        return children;
    }
}
