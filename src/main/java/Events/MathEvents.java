package Events;

import Bot.DiscordBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MathEvents extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(DiscordBot.prefix + "calc")) {

            if(args.length < 3) {

            }
        }
    }

    public boolean isNumber(String str) {
        try {
            double number = Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
