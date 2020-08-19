package Events;

import Bot.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Random;

public class RandomEvent extends ListenerAdapter {
    private EmbedBuilder card = new EmbedBuilder();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        card.setColor(Color.BLUE);

        String message = event.getMessage().getContentRaw();
        String[] args = message.split("//s+");
        if(!args[0].substring(0, 1).equals(DiscordBot.prefix)){ //If the prefix is not present, end method and do nothing
            return;
        }
        String command = args[0].substring(1);

        if (command.equalsIgnoreCase("dice") || command.equalsIgnoreCase("roll")){
            rollDice(event, args);
        }
        else if (command.equalsIgnoreCase("decide")){
            randomize(event, args);
        }

    }

    private void randomize(GuildMessageReceivedEvent event, String[] args) {
        Random random = new Random();
        if(args.length < 3){
            SendErrorMessage(event, "At least provide 2 things to randomize");
            return;
        }
        String[] words = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            words[i-1] = args[i];
        }
        int randomIndex = random.nextInt(words.length);
        String randomWord = words[randomIndex];
        SendMessage(event, "A decision has been made!", "Your result: " + randomWord);
    }

    private void rollDice(@Nonnull GuildMessageReceivedEvent event, String[] args) {
        Random random = new Random();
        int diceRollValue = 0;

        if (args.length == 1){
            diceRollValue = random.nextInt(6) + 1;
        }
        else if(args.length == 2){
            int numberOfSides;
            try {
                numberOfSides = Integer.parseInt(args[1]);
                if (numberOfSides < 2) {
                    SendErrorMessage(event, "The die must have at least 2 sides");
                    return;
                }
            } catch (NumberFormatException e){
                SendErrorMessage(event, "Number of sides must be a whole number");
                return;
            }
            diceRollValue = random.nextInt(numberOfSides) + 1;
        }
        else {
            SendErrorMessage(event, "Only 1 argument accepted after roll/dice command");
            return;
        }

        SendMessage(event, "Rolling dice!", "You rolled a " + diceRollValue);
    }

    private void SendMessage(@Nonnull GuildMessageReceivedEvent event, String title, String message) {
        card.setTitle(title);
        card.setDescription(message);
        event.getChannel().sendMessage(card.build()).queue();
    }

    private void SendErrorMessage(@Nonnull GuildMessageReceivedEvent event, String message) {
        card.setColor(Color.RED);
        SendMessage(event, "Invalid command!", message);
    }

}
