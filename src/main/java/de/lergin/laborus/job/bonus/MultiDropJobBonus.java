package de.lergin.laborus.job.bonus;

import de.lergin.laborus.api.JobAction;
import de.lergin.laborus.api.JobBonus;
import de.lergin.laborus.job.Job;
import de.lergin.laborus.api.JobItem;
import de.lergin.laborus.util.BonusHelper;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.item.inventory.ItemStack;

/**
 * bonus drop of the block
 */
@ConfigSerializable
public class MultiDropJobBonus extends JobBonus {
    @Setting(value = "extraDrops", comment = "amount of times the item gets dropped extra")
    private int extraDrops = 0;

    @Override
    public void applyBonus(JobItem item, Player player, Object item2) {
        if (item2 instanceof BlockState) {
            ItemStack itemStack = ItemStack.builder().fromBlockState((BlockState) item2).quantity(extraDrops).build();

            BonusHelper.dropItem(player.getLocation(), itemStack);
        }
    }

    @Override
    public boolean canHappen(Job job, JobAction jobAction, JobItem jobItem, Player player) {
        return jobItem.getItem() instanceof String && super.canHappen(job, jobAction, jobItem, player);
    }

    public MultiDropJobBonus() {}
}
