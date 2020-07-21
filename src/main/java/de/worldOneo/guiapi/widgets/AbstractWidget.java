package de.worldOneo.guiapi.widgets;

import de.worldOneo.guiapi.GUIManager;
import de.worldOneo.guiapi.gui.IGUI;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.entity.Player;

@Accessors(chain = true)
@Getter
@Setter
public abstract class AbstractWidget implements IWidget {
    private int slot;
    private IGUI igui;


    /**
     * This function is used to support {@link AbstractWidget#open(Player)}
     *
     * @param igui the GUI to add this Widget to
     */
    public void addToGUI(IGUI igui) {
        igui.addWidget(this);
        this.setIgui(igui);
    }

    protected void open(Player player) {
        GUIManager.getInstance().open(getIgui(), player);
    }
}