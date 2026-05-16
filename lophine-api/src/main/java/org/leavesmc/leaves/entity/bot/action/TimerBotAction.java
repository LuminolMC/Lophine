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

package org.leavesmc.leaves.entity.bot.action;

/**
 * Represents a scheduled bot task that runs periodically.
 * <p>
 * TimerBotAction allows configuration of start delay, execution interval, and the number of executions.
 * It is intended for bot actions that need to be triggered at regular intervals.
 *
 * @param <E> the type of entity this action operates on
 */
public interface TimerBotAction<E> extends BotAction<E> {

    /**
     * Sets the delay in ticks before the task starts for the first time.
     *
     * @param delayTick the number of ticks to delay before the first execution
     */
    void setStartDelayTick(int delayTick);

    /**
     * Gets the delay in ticks before the task starts for the first time.
     *
     * @return the number of ticks to delay before the first execution
     */
    int getStartDelayTick();

    /**
     * Sets the interval in ticks between each execution of the task.
     *
     * @param intervalTick the number of ticks between executions
     */
    void setDoIntervalTick(int intervalTick);

    /**
     * Gets the interval in ticks between each execution of the task.
     *
     * @return the number of ticks between executions
     */
    int getDoIntervalTick();

    /**
     * Sets the total number of times the task should be executed.
     *
     * @param doNumber the total number of executions
     */
    void setDoNumber(int doNumber);

    /**
     * Gets the total number of times the task should be executed.
     *
     * @return the total number of executions
     */
    int getDoNumber();

    /**
     * Gets the number of ticks remaining until the next execution.
     *
     * @return the number of ticks until the next execution
     */
    int getTickToNext();

    /**
     * Gets the number of executions remaining for this task.
     *
     * @return the number of executions remaining
     */
    int getDoNumberRemaining();
}
