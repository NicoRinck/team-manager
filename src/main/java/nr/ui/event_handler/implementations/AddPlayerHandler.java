package nr.ui.event_handler.implementations;

import nr.Form;
import nr.data_manager.DataManager;
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;
import nr.ui.components.EntityList;
import nr.ui.event_handler.AddEntityToListHandler;

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
        Optional<PlayerAttributes> optional = entityForm.showCreateAttributesForm();

        optional.ifPresent(playerAttributes -> {
                    Player player = new Player(playerAttributes);
                    dataManager.saveEntity(player);
                }

        );
    }
}
