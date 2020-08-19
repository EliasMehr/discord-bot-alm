package Events;

import Bot.DiscordBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import static Bot.DiscordBot.prefix;

public class MathEvents extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(prefix + "calc")) {
            if(args.length < 3) {
                event.getChannel().sendMessage("Wrong input, correct Format is " +
                        " [Number] [Operator] [Number]").queue();
            }

            else if(isNumber(args[1]) && isOperator(args[2]) && isNumber(args[3])) {
                char operator = args[2].charAt(0);
                event.getChannel().sendMessage(args[1] + " " + args[2] + " " + args[3] + " = " + calcResult(args)).queue();
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

    public boolean isOperator(String str) {
        if(str.length() == 1) {
            switch(str) {
                case "+":
                case "-":
                case "*":
                case "/":
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public double calcResult(String[] args) {
        double firstNumber = Double.parseDouble(args[1]);
        double secondNumber = Double.parseDouble(args[3]);
        double result = 0;
        switch(args[2]) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            case "*" -> result = firstNumber * secondNumber;
        }
        return result;
    }
}
