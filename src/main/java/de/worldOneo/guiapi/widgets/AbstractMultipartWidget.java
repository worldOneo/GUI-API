package de.worldOneo.guiapi.widgets;

import de.worldOneo.guiapi.GUIManager;
import de.worldOneo.guiapi.gui.IGUI;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.entity.Player;

@Accessors(chain = true)
@Setter
@Getter
public abstract class AbstractMultipartWidget implements IMultipartWidget {
    private IGUI igui;

    public void addToGUI(IGUI igui) {
        igui.addWidget(this);
        this.igui = igui;
    }

    protected void open(Player player) {
        GUIManager.getInstance().open(this.igui, player);
    }
}
