package link.liycxc.pvp.management.quickplay.impl;

import java.util.ArrayList;

import link.liycxc.pvp.management.quickplay.QuickPlay;
import link.liycxc.pvp.management.quickplay.QuickPlayCommand;

public class SkywarsQuickPlay extends QuickPlay{

	public SkywarsQuickPlay() {
		super("Skywars", "soar/mods/quickplay/Skywars.png");
	}

	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/l s"));
		commands.add(new QuickPlayCommand("Solo Normal", "/play solo_normal"));
		commands.add(new QuickPlayCommand("Solo Insane", "/play solo_insane"));
		commands.add(new QuickPlayCommand("Teams Normal", "/play teams_normal"));
		commands.add(new QuickPlayCommand("Teams Insane", "/play teams_insane"));
		commands.add(new QuickPlayCommand("Mega", "/play mega_normal"));
		
		this.setCommands(commands);
	}
}
