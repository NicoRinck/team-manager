package tm.ui.event_handler.implementations;

import tm.data_manager.DataManager;
import tm.data_model.entities.player.Player;
import tm.data_model.entities.player.PlayerAttributes;
import tm.ui.components.EntityList;
import tm.ui.event_handler.AddEntityToListHandler;
import tm.ui.forms.Form;

import java.util.Optional;

public class AddPlayerHandler implements AddEntityToListHandler<Player> {

    private final Form<PlayerAttributes> entityForm;
    private final DataManager<Player> dataManager;

    public AddPlayerHandler(Form<PlayerAttributes> entityForm, DataManager<Player> dataManager) {
        this.dataManager = dataManager;
        this.entityForm = entityForm;
    }

    @Override
    public void addEntityToList(EntityList<Player> entityList) {
        final Optional<PlayerAttributes> optional = entityForm.showCreateAttributesForm();

        optional.ifPresent(playerAttributes -> {
                    Player player = new Player(playerAttributes);
                    dataManager.saveEntity(player);
                }

        );
    }
}
