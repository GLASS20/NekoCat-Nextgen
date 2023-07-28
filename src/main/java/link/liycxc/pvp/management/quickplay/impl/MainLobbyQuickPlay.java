package link.liycxc.pvp.management.quickplay.impl;

import link.liycxc.pvp.management.quickplay.QuickPlay;
import link.liycxc.pvp.management.quickplay.QuickPlayCommand;

import java.util.ArrayList;

public class MainLobbyQuickPlay extends QuickPlay{

	public MainLobbyQuickPlay() {
		super("MainLobby", "soar/mods/quickplay/MainLobby.png");
	}

	@Override
	public void addCommands(){
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/lobby main"));
		
		this.setCommands(commands);
	}
}
