package music.commands;

import core.Command;
import core.CommandDescription;
import music.MusicManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

@CommandDescription(
		name = {"skip", "next"}
)
public class SkipCommand extends Command {
	@Override
	public boolean execute(List<String> args, MessageReceivedEvent event) {
		MusicManager.interacted(event.getTextChannel(), event.getAuthor().getIdLong());
		MusicManager.skip(event.getGuild().getIdLong());
		return false;
	}
}
