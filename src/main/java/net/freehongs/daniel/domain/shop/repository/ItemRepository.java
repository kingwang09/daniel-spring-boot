package net.freehongs.daniel.domain.shop.repository;

import net.freehongs.daniel.domain.shop.constant.ItemStatus;
import net.freehongs.daniel.domain.shop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.id in (:idList) and i.status = :status")
    Set<Item> findItemsByIdList(Set<Long> idList, ItemStatus status);
}
