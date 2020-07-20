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
public abstract class AbstractWidget implements Widget {
    private int slot;
    private IGUI igui;

    @Override
    public void beforeRender() {

    }

    public void addToGUI(IGUI igui){
        igui.addWidget(this);
        this.setIgui(igui);
    }

    protected void open(Player player){
        GUIManager.getInstance().open(getIgui(), player);
    }
}