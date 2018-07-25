package tm.ui.event_handler.implementations;

import tm.data_manager.DataManager;
import tm.data_model.entities.player.Player;
import tm.data_model.entities.player.PlayerAttributes;
import tm.ui.event_handler.EditEntityHandler;
import tm.ui.forms.Form;
import tm.ui.views.EntityDetailView;

public class EditPlayerHandler implements EditEntityHandler<Player> {

    private final DataManager<Player> dataManager;
    private final Form<PlayerAttributes> entityForm;

    public EditPlayerHandler(DataManager<Player> dataManager, Form<PlayerAttributes> entityForm) {
        this.dataManager = dataManager;
        this.entityForm = entityForm;
    }

    @Override
    public void editEntity(Player player, EntityDetailView<Player> entityDetailView) {
        entityForm.showEditAttributesForm(player.getPlayerAttributes()).ifPresent(playerAttributes -> {
            player.setPlayerAttributes(playerAttributes);
            dataManager.editEntity(player);
            entityDetailView.updateDetailView();

        } );

    }
}
