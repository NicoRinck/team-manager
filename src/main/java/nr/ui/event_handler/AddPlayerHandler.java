package nr.ui.event_handler;

import nr.Form;
import nr.data_manager.DataManager;
import nr.data_model.entities.player.Player;
import nr.data_model.entities.player.PlayerAttributes;

import java.util.Optional;

public class AddPlayerHandler implements AddEntityToListHandler<Player> {

    private final Form<PlayerAttributes> entityForm;
    private final DataManager<Player> dataManager;

    public AddPlayerHandler(Form<PlayerAttributes> entityForm, DataManager<Player> dataManager) {
        this.dataManager = dataManager;
        this.entityForm = entityForm;
    }

    @Override
    public void addEntityToList() {
        Optional<PlayerAttributes> optional = entityForm.showCreateAttributesForm();
        System.out.println(optional.isPresent());
        optional.ifPresent(playerAttributes -> dataManager.saveEntity(new Player(playerAttributes)));
    }
}
