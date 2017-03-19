package de.lergin.sponge.laborus.config;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.spongepowered.api.text.TextTemplate.arg;

@ConfigSerializable
public class TranslationConfig {
    @Setting(value = "JOB_XP_ACTION_BAR", comment = "The Text shown in the action bar after getting xp")
    TextTemplate JOB_XP_ACTION_BAR = TextTemplate.of(
            arg("job.name").build(),
            ": ",
            arg("job.xp").build()
    );

    @Setting(value = "JOB_LEVEL_UP", comment = "The Text shown when a player gets a level up")
    TextTemplate JOB_LEVEL_UP = TextTemplate.of(
            "You have reached level ",
            arg("job.level").color(TextColors.GREEN).build(),
            " in the job ",
            arg("job.name").color(TextColors.GREEN).build()
    );

    @Setting(value = "JOB_LEVEL_NOT_HIGH_ENOUGH", comment = "The Text shown when a player has a to small level to do something")
    TextTemplate JOB_LEVEL_NOT_HIGH_ENOUGH = TextTemplate.of(
            "Your level is too low to do this."
    );

    @Setting(value = "JOB_ABILITY_CANNOT_START_COOLDOWN", comment = "")
    TextTemplate JOB_ABILITY_CANNOT_START_COOLDOWN = TextTemplate.of(
            "You used the ability ", arg("ability.name").color(TextColors.GREEN).build(),
            " from ",
            arg("job.name").color(TextColors.GREEN).build(),
            " recently so you need to wait ",
            arg("ability.remaining_cooldown").color(TextColors.GREEN), "s until you can use it again."
    );

    @Setting(value = "JOB_ABILITY_START", comment = "")
    TextTemplate JOB_ABILITY_START = TextTemplate.of(
            "You started the ability ",
            arg("ability.name").color(TextColors.GREEN).build(),
            " from ", arg("job.name").color(TextColors.GREEN).build(),
            ". You can use it again in ",
            arg("ability.remaining_cooldown").color(TextColors.GREEN), "s."
    );

    @Setting(value = "COMMAND_ADDXP_SEND_OTHER", comment = "")
    TextTemplate COMMAND_ADDXP_SEND_OTHER = TextTemplate.of(
            "You added ",
            arg("xp").color(TextColors.GREEN).build(),
            Text.of(TextColors.GREEN, "xp"),
            " to ", arg("player.display_name").build(),
            " job ", arg("job.name").color(TextColors.GREEN).build(),
            "."
    );

    @Setting(value = "COMMAND_ADDXP_RECEIVE_OTHER", comment = "")
    TextTemplate COMMAND_ADDXP_RECEIVE_OTHER = TextTemplate.of(
            arg("source").color(TextColors.GREEN).build(),
            " added ", arg("xp").color(TextColors.GREEN).build(),
            Text.of(TextColors.GREEN, "xp"),
            " to your job ", arg("job.name").color(TextColors.GREEN).build(),
            "."
    );

    @Setting(value = "COMMAND_ADDXP_SELF", comment = "")
    TextTemplate COMMAND_ADDXP_SELF = TextTemplate.of(
            "You added ",
            arg("xp").color(TextColors.GREEN).build(),
            Text.of(TextColors.GREEN, "xp"),
            " to ", arg("job.name").color(TextColors.GREEN).build(),
            "."
    );

    @Setting(value = "COMMAND_SETXP_SEND_OTHER", comment = "")
    TextTemplate COMMAND_SETXP_SEND_OTHER = TextTemplate.of(
            "You have set the xp of the job ", arg("job.name").color(TextColors.GREEN).build(),
            " from the ", arg("player.display_name").build(),
            " to ", arg("xp").color(TextColors.GREEN).build(),
            "."
    );

    @Setting(value = "COMMAND_SETXP_RECEIVE_OTHER", comment = "")
    TextTemplate COMMAND_SETXP_RECEIVE_OTHER = TextTemplate.of(
            arg("source").color(TextColors.GREEN).build(),
            " has set your xp of ", arg("job.name").color(TextColors.GREEN).build(),
            " to ", arg("xp").color(TextColors.GREEN).build(), "."
    );

    @Setting(value = "COMMAND_SETXP_SELF", comment = "")
    TextTemplate COMMAND_SETXP_SELF = TextTemplate.of(
            "You set the xp of ", arg("job.name").color(TextColors.GREEN).build(),
            " to ", arg("xp").color(TextColors.GREEN).build(),
            Text.of(TextColors.GREEN, "xp"), "."
    );

    @Setting(value = "COMMAND_CHANGE_JOINED", comment = "")
    TextTemplate COMMAND_CHANGE_JOINED = TextTemplate.of(
            "You have joined ", arg("job.name").color(TextColors.GREEN).build(), "."
    );

    @Setting(value = "COMMAND_CHANGE_ALREADY_JOINED", comment = "")
    TextTemplate COMMAND_CHANGE_ALREADY_JOINED = TextTemplate.of(
            "You already joined ", arg("job.name").color(TextColors.GREEN).build(), "."
    );

    @Setting(value = "COMMAND_CHANGE_LEAVED", comment = "")
    TextTemplate COMMAND_CHANGE_LEAVED = TextTemplate.of(
            "You have leaved ", arg("job.name").color(TextColors.GREEN).build(), "."
    );

    @Setting(value = "COMMAND_CHANGE_NOT_SELECTED", comment = "")
    TextTemplate COMMAND_CHANGE_NOT_SELECTED = TextTemplate.of(
            "You don't had ", arg("job.name").color(TextColors.GREEN).build(), " selected."
    );

    @Setting(value = "COMMAND_CHANGE_MISSING_JOB_PERMISSION", comment = "")
    TextTemplate COMMAND_CHANGE_MISSING_JOB_PERMISSION = TextTemplate.of(
            "You cannot join ", arg("job.name").color(TextColors.GREEN).build(),
            " (missing permission)"
    );

    @Setting(value = "COMMAND_CHANGE_TOO_MANY_SELECTED", comment = "")
    TextTemplate COMMAND_CHANGE_TOO_MANY_SELECTED = TextTemplate.of(
            "You cannot join ", arg("job.name").color(TextColors.GREEN).build(),
            ", due to the reach of the max. of jobs you can select (",
            arg("maxjobs").color(TextColors.GREEN).build(),
            "). If you want to join another job you first need to ",
            Text.builder("leave")
                    .onClick(TextActions.suggestCommand("/jobs change leave "))
                    .onHover(TextActions.showText(Text.of("/jobs change leave ")))
                    .style(TextStyles.UNDERLINE).build(),
            " another one."
    );

    @Setting(value = "COMMAND_TOGGLE_ACTIVATED", comment = "")
    TextTemplate COMMAND_TOGGLE_ACTIVATED = TextTemplate.of("Enabled JobSystem!");

    @Setting(value = "COMMAND_TOGGLE_DEACTIVATED", comment = "")
    TextTemplate COMMAND_TOGGLE_DEACTIVATED = TextTemplate.of("Disabled JobSystem!");

    @Setting(value = "COMMAND_INFO_SINGLE", comment = "")
    TextTemplate COMMAND_INFO_SINGLE = TextTemplate.of(
            "==================== ", arg("job.name").color(TextColors.GREEN).style(TextStyles.BOLD).build(),
            " ====================", "\n",
            arg("job.description").build(), "\n",
            "Current XP: ", arg("job.xp").color(TextColors.GREEN).build(), "\n",
            "Current Level: ", arg("job.level").color(TextColors.GREEN).build(), "\n",
            "XP till next Level: ", arg("job.xp_till_next_level").color(TextColors.GREEN).build(), "\n",
            "Selected: ", arg("job.selected").color(TextColors.GREEN).build(), "\n"

    );

    @Setting(value = "COMMAND_INFO_HEADER", comment = "")
    TextTemplate COMMAND_INFO_HEADER = TextTemplate.of(
            "======================= ",
            Text.builder("Jobs").style(TextStyles.BOLD).color(TextColors.GREEN).build(),
            " ========================"
    );


    @Setting(value = "COMMAND_INFO_LINE", comment = "")
    TextTemplate COMMAND_INFO_LINE = TextTemplate.of(
            arg("job.name").color(TextColors.GREEN).style(TextStyles.BOLD).build(),
            "  Level: ", arg("job.level").color(TextColors.GREEN).build(), "  Xp: ",
            arg("job.xp").color(TextColors.GREEN), " / ", arg("job.xp_for_next_level").color(TextColors.GREEN),
            "  Selected: ", arg("job.selected").color(TextColors.GREEN).build()
    );

    @Setting(value = "COMMAND_INFO_FOOTER", comment = "")
    TextTemplate COMMAND_INFO_FOOTER = TextTemplate.of("");

    @Setting(value = "COMMAND_RELOAD_START", comment = "")
    TextTemplate COMMAND_RELOAD_START = TextTemplate.of(TextColors.RED, "Started reloading the configuration!");

    @Setting(value = "COMMAND_RELOAD_SUCCESS", comment = "")
    TextTemplate COMMAND_RELOAD_SUCCESS = TextTemplate.of(TextColors.RED, "Reloaded the configuration!");

    @Setting(value = "COMMAND_RELOAD_ERROR", comment = "")
    TextTemplate COMMAND_RELOAD_ERROR = TextTemplate.of(TextColors.RED, "Couldn't reload the configuration!");

    private Map<String, TextTemplate> additionalTranslations;

    public TranslationConfig(){}

    public TextTemplate get(TranslationKeys key, String additionalId){
        if(!Objects.equals(additionalId, "")){
            return additionalTranslations.getOrDefault(key.toString().concat("_").concat(additionalId), key.get(this));
        }

        return key.get(this);
    }

    public TextTemplate get(TranslationKeys key){
        return get(key, "");
    }

    public void initJobSpecificMessages(ConfigurationNode node){
        additionalTranslations = new HashMap<>();
        Map<Object, ? extends ConfigurationNode> nodes = node.getChildrenMap();

        Set<Object> keys = nodes.keySet();

        keys.stream().filter(
                key -> {
                    for(TranslationKeys transKey : TranslationKeys.values()){
                        if(key.toString().equals(transKey.name())){
                            return false;
                        }
                    }

                    return true;
                }
        ).forEach(key->{
            try {
                additionalTranslations.put(key.toString(), nodes.get(key).getValue(TypeToken.of(TextTemplate.class)));
            } catch (ObjectMappingException e) {
                e.printStackTrace();
            }
        });
    }

    public void saveJobSpecificMessages(ConfigurationNode node){
        additionalTranslations.forEach((key, trans)->{
            try {
                System.out.println(key);
                node.getNode(key).setValue(TypeToken.of(TextTemplate.class), trans);
            } catch (ObjectMappingException e) {
                e.printStackTrace();
            }
        });
    }
}
