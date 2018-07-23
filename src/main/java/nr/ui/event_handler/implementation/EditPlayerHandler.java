package nr.ui.event_handler.implementation;

import nr.Form;
import nr.data_manager.DataManager;
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.event_handler.EditEntityHandler;

public class EditPlayerHandler implements EditEntityHandler<Player> {

    private final DataManager<Player> dataManager;
    private final Form<PlayerAttributes> entityForm;

    public EditPlayerHandler(DataManager<Player> dataManager, Form<PlayerAttributes> entityForm) {
        this.dataManager = dataManager;
        this.entityForm = entityForm;
    }

    @Override
    public void editEntity(Player player) {
        entityForm.showEditAttributesForm(player.getPlayerAttributes()).ifPresent(playerAttributes -> {
            player.setPlayerAttributes(playerAttributes);
            dataManager.editEntity(player);
            //TODO: 1.Fehler nicht in DB,  2.Fehler DetailView aktualisieren
            System.out.println(player);
        } );

    }
}
