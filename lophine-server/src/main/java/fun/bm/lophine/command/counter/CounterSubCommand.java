package fun.bm.lophine.command.counter;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.command.LiteralNode;

public class CounterSubCommand extends LiteralNode {
    protected final CounterCommand parent;

    protected CounterSubCommand(String name, CounterCommand parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean requires(@NotNull CommandSourceStack source) {
        return hasPermission(source.getSender());
    }

    protected boolean hasPermission(CommandSender sender) {
        return parent.hasPermission(sender, this.name);
    }
}
