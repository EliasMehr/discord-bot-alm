package Events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


import static Bot.DiscordBot.PREFIX;

import static Bot.DiscordBot.prefix;


public class InvitationEvent extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");


        if (args[0].equalsIgnoreCase(PREFIX + "invite")) {

        if (args[0].equalsIgnoreCase(prefix + "invite")) {

            String invitationURL = event.getChannel().createInvite().complete().getUrl();
            String invitedBy = event.getAuthor().getName();

            event.getChannel()
                    .sendMessage("Hey you!, " + invitedBy + " give your friend this friendly link to join us.. " + invitationURL)
                    .queue();
        }
    }
}
