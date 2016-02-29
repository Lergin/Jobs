package de.lergin.sponge.jobs.job.bonus;

import de.lergin.sponge.jobs.job.JobBonus;
import de.lergin.sponge.jobs.job.JobItem;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.extent.Extent;

import java.util.Optional;

/**
 * bonus drop of the block
 */
public class MultiDrop extends JobBonus {
    int dropMultiplier;

    @Override
    public void useBonus(JobItem item, Player player) {
        if(this.isHappening()){
            Optional<ItemType> optionalItemType = ((BlockType) item.getItem()).getItem();

            if(optionalItemType.isPresent()){
                ItemStack itemStack = ItemStack.of(optionalItemType.get(), dropMultiplier);

                Extent extent = player.getLocation().getExtent();

                Optional<Entity> itemEntity = extent.createEntity(EntityTypes.ITEM, player.getLocation().getPosition());

                if(itemEntity.isPresent()){
                    Entity entity = itemEntity.get();

                    entity.offer(Keys.REPRESENTED_ITEM, itemStack.createSnapshot());
                    extent.spawnEntity(entity, Cause.of(player));

                    if(isSendMessage()){
                        player.sendMessage(getMessage());
                    }
                }
            }
        }
    }

    @Override
    public boolean canHappen(JobItem jobItem, Player player) {
        return jobItem.getItem() instanceof BlockType;
    }

    public MultiDrop(ConfigurationNode config) {
        super(
                config.getNode("probability").getDouble(0.05),
                config.getNode("sendMessage").getBoolean(false),
                Text.of(config.getNode("message").getString(""))
        );

        //we only need to do the extra drop
        this.dropMultiplier = config.getNode("itemMultiplier").getInt(2) - 1;
    }
}