/**
 * The class that stores which players cannot use commands, and for how long
 * @author dririan
 */

import java.util.ArrayList;

public class CommandDisabledPlayer {
    private String name;
    private long expiryTime;
    public CommandDisabledPlayer(String playerName, long expiresAt) {
        name = playerName;
        expiryTime = expiresAt;
    }

    public static boolean resetTime(String playerName, long newTime, ArrayList<CommandDisabledPlayer> disabledPlayerList) {
        for(CommandDisabledPlayer player : disabledPlayerList) {
            if(player.name.equals(playerName)) {
                player.setExpires(newTime);
                return true;
            }
        }
        return false;
    }

    public long getExpires() {
        return expiryTime;
    }

    public String getName() {
        return name;
    }

    public void setExpires(long expiresAt) {
        expiryTime = expiresAt;
    }

    public static long isDisabled(String playerName, ArrayList<CommandDisabledPlayer> disabledPlayerList) {
        for(CommandDisabledPlayer player : disabledPlayerList) {
            if(player.name.equals(playerName) && !player.isExpired())
                return (player.expiryTime - System.currentTimeMillis()/1000);
        }
        return 0;
    }

    public boolean isExpired() {
        if(System.currentTimeMillis()/1000 > expiryTime)
            return true;
        return false;
    }
}
