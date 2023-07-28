package link.liycxc.pvp.management.quickplay.impl;

import link.liycxc.pvp.management.quickplay.QuickPlay;
import link.liycxc.pvp.management.quickplay.QuickPlayCommand;

import java.util.ArrayList;

public class BlitzSGQuickPlay extends QuickPlay{

	public BlitzSGQuickPlay() {
		super("BlitzSG", "soar/mods/quickplay/Blitz.png");
	}

	
	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/l hg"));
		commands.add(new QuickPlayCommand("Solo", "/play blitz_solo_normal"));
		commands.add(new QuickPlayCommand("Teams", "/play blitz_teams_normal"));
		
		this.setCommands(commands);
	}
}
