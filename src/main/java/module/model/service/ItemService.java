package module.model.service;

import module.model.dto.ItemDTO;
import module.model.dto.ItemIdAndDateDTO;
import module.model.dto.StatisticsDTO;
import module.model.entity.Item;
import module.model.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

public class ItemService {
    private final ItemRepository repository;

    public ItemService() {
        this.repository = new ItemRepository();
    }

    public void saveItem(Item item) {
        repository.save(item);
    }

    public Optional<ItemDTO> findItemById(String id) {
        return repository.getById(id);
    }

    public List<ItemIdAndDateDTO> findAllItems() {
        return repository.getAll();
    }

    public  Optional<StatisticsDTO> getStatistics() {
        return repository.getStatistics();
    }

}
