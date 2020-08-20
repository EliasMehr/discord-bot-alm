package Events;

import Handlers.MathHandlers;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;

import static Bot.DiscordBot.PREFIX;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

public class MathEvents extends ListenerAdapter {
    MathHandlers mathHandlers = new MathHandlers();
    EmbedBuilder iCard = new EmbedBuilder();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {


        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(PREFIX + "calc")) {
            if(args.length < 3) {
                iCard.setColor(RED);
                iCard.setTitle("Bad inputs", null);
                iCard.setDescription("Wrong input, correct Format is " +
                        " [Number] space [Operator] space [Number]");
                event
                        .getChannel()
                        .sendMessage(iCard.build())
                        .queue();
            }

            else if(args[1].matches("\\d+") && mathHandlers.isOperator(args[2]) && args[3].matches("\\d+")) {
                iCard.setColor(GREEN);
                iCard.setTitle("Result", null);
                iCard.setDescription(args[1] + " " + args[2] + " " + args[3] + " = " + mathHandlers.calcResult(args));

                event
                        .getChannel()
                        .sendMessage(iCard.build())
                        .queue();
            }
        }
    }

}
