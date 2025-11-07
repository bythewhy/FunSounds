package funpay.neverlos3.funSounds.util;

import org.bukkit.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    private static final Pattern hex = Pattern.compile("#[A-Fa-f0-9]{6}");

    public static String pars(String message) {
        Matcher matcher = hex.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hex = matcher.group();
            StringBuilder replacement = new StringBuilder("§x");
            for (char c : hex.substring(1).toCharArray()) {
                replacement.append('§').append(c);
            }
            matcher.appendReplacement(buffer, replacement.toString());
        }
        matcher.appendTail(buffer);
        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }
}