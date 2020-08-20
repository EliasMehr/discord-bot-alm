package Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

import static Bot.DiscordBot.PREFIX;
import static java.awt.Color.BLUE;

public class HelperEvent extends ListenerAdapter {
    private EmbedBuilder card = new EmbedBuilder();
    private String helpText = "$calc <value> <operator> <value> => Mathematic Calculator\n\n" +
            "$delete <value (0-100)> => Deletes the amount of messages you put in.\n\n" +
            "$dice or $roll <optional value> => Let's you roll a six sided dice. Optional value changes the number of sides.\n\n" +
            "$decide <values...> (at least 2 values) => Let's you get a random result from your inputed values.\n\n" +
            "$invite => Gives you the invitation link to the channel";

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(PREFIX + "help")) {
            card.setTitle("ℹ️ Hey there! This is Dr.Strange's available commands. You yell, and I obey");
            card.setDescription(helpText);
            card.setColor(BLUE);
            event.getChannel().sendMessage(card.build()).queue();
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        Member member = event.getMember();

        card.setTitle("ℹ️ Welcome " + member.getEffectiveName() + "! This is Dr.Strange's available commands. You yell, and I obey");
        card.setDescription(helpText);
        card.setColor(BLUE);
        event.getGuild().getDefaultChannel().sendMessage(card.build()).queue();
    }
}
