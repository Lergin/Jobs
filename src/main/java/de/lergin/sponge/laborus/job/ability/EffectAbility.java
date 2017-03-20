package de.lergin.sponge.laborus.job.ability;

import de.lergin.sponge.laborus.Laborus;
import de.lergin.sponge.laborus.job.Job;
import de.lergin.sponge.laborus.job.JobAbility;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectType;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class EffectAbility extends JobAbility {
    private Laborus plugin = Laborus.instance();

    @Setting(value = "potionEffect", comment = "the settings of the potion effect")
    private PotionEffectConfig effectConfig = new PotionEffectConfig();

    public EffectAbility() {}

    @Override
    public boolean startAbility(Job job, Player player) {
        if (!canStartAbility(job, player)) {
            sendCoolDownNotEndedMessage(job, player);
            return false;
        }

        List<PotionEffect> potionEffects = player.get(Keys.POTION_EFFECTS).orElseGet(ArrayList::new);
        potionEffects.add(effectConfig.get());
        player.offer(Keys.POTION_EFFECTS, potionEffects);

        startCoolDown(job, player);
        sendStartMessage(job, player);

        plugin.config.base.loggingConfig.jobAbilities(job, "Started EffectAbility ({})", potionEffects.toString());

        return true;
    }

    @ConfigSerializable
    private static class PotionEffectConfig {
        @Setting(comment = "the amplifier of the effect, see http://minecraft.gamepedia.com/Commands#effect")
        int amplifier = 0;
        @Setting(comment = "the duration of the effect, see http://minecraft.gamepedia.com/Commands#effect")
        int duration = 1;
        @Setting
        boolean ambiance = false;
        @Setting(comment = "if particles are shown")
        boolean particles = true;
        @Setting(comment = "the name of the type of the potion, see http://minecraft.gamepedia.com/Data_values#Status_effects names")
        PotionEffectType potionType = PotionEffectTypes.SPEED;

        public PotionEffect get(){
            return PotionEffect.builder()
                    .amplifier(amplifier)
                    .duration(duration)
                    .particles(particles)
                    .potionType(potionType)
                    .ambience(ambiance)
                    .build();
        }

        public PotionEffectConfig(){}
    }
}
