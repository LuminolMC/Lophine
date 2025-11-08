/*
 * This file is part of Leaves (https://github.com/LeavesMC/Leaves)
 *
 * Leaves is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Leaves is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Leaves. If not, see <https://www.gnu.org/licenses/>.
 */

package org.leavesmc.leaves;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LeavesLogger extends Logger {
    public static final LeavesLogger LOGGER = new LeavesLogger();

    private LeavesLogger() {
        super("Leaves", null);
        setParent(Bukkit.getLogger());
        setLevel(Level.ALL);
    }

    public void severe(String msg, Exception exception) {
        this.log(Level.SEVERE, msg, exception);
    }

    public void warning(String msg, Exception exception) {
        this.log(Level.WARNING, msg, exception);
    }
}
