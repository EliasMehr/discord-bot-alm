package Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static Bot.DiscordBot.PREFIX;
import static java.awt.Color.*;

public class ChannelHistoryEvent extends ListenerAdapter {

    EmbedBuilder iCard = new EmbedBuilder();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(PREFIX + "delete")) {
            if (args.length < 2) {
                iCard.setTitle("Hey there!, here's your instructions");
                iCard.setDescription("To use this delete command you need to type it like this: <PREFIX><command> <value>");
                iCard.setColor(ORANGE);

                event
                        .getChannel()
                        .sendMessage(iCard.build())
                        .queue();
            } else try {
                List<Message> messageHistory = event.getChannel()
                        .getHistory()
                        .retrievePast(Integer.parseInt(args[1]))
                        .complete();

                event
                        .getChannel()
                        .deleteMessages(messageHistory)
                        .complete();

                iCard.setColor(GREEN);
                iCard.setTitle("Success");
                iCard.setDescription("Your request is resolved");

                event
                        .getChannel()
                        .sendMessage(iCard.build())
                        .queue();
            } catch (IllegalArgumentException exception) {
                if (exception.toString().equals("Message retrieval")) {
                    iCard.setTitle("Hey hold on!");
                    iCard.setDescription("You're trying to delete more messages than you can. Range is 1 - 100");
                    iCard.setColor(RED);

                    event
                            .getChannel()
                            .sendMessage(iCard.build())
                            .queue();
                } else {
                    iCard.setTitle("Hey hold on!");
                    iCard.setDescription("You're trying to delete messages older than 2 weeks!");
                    iCard.setColor(RED);

                    event
                            .getChannel()
                            .sendMessage(iCard.build())
                            .queue();
                }
            }
        }
    }
}
