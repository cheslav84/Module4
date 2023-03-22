package module.model.repository;

import module.model.dto.ItemDTO;
import module.model.dto.ItemIdAndDateDTO;
import module.model.dto.StatisticsDTO;
import module.model.entity.Item;
import module.model.util.HibernateFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ItemRepository {
    private final EntityManager entityManager;

    public ItemRepository() {
        this.entityManager = HibernateFactoryUtil.getEntityManager();
    }

    public void save(Item item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<ItemDTO> getById(String id) {
        ItemDTO item = entityManager.createQuery(
                        """
                                SELECT new module.model.dto.ItemDTO (
                                    i.id,
                                    i.productionDuration,
                                    i.productionDate,
                                    i.brokenMicrocircuits,
                                    f.producedAmount,
                                    f.spentAmount
                                )
                                FROM Item i
                                JOIN i.fuel f
                                WHERE i.id = :itemId
                                """,
                        ItemDTO.class)
                .setParameter("itemId", id)
                .getSingleResult();
        return Optional.of(item);
    }

    public List<ItemIdAndDateDTO> getAll() {
        return entityManager.createQuery(
                        """
                                SELECT new module.model.dto.ItemIdAndDateDTO (
                                    i.id,
                                    i.productionDate
                                )
                                FROM Item i
                                """,
                        ItemIdAndDateDTO.class)
                .getResultList();
    }

    public Optional<StatisticsDTO> getStatistics() {
        StatisticsDTO statistics = entityManager.createQuery(
                        """
                                SELECT new module.model.dto.StatisticsDTO (
                                    COUNT(*),
                                    SUM(i.brokenMicrocircuits),
                                    SUM(f.producedAmount),
                                    SUM(f.spentAmount)
                                )
                                FROM Item i
                                JOIN i.fuel f
                                """,
                        StatisticsDTO.class)
                .getSingleResult();
         return Optional.of(statistics);
    }


}
