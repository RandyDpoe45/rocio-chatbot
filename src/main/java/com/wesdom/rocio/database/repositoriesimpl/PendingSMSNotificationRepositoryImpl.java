package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.PendingSMSNotificationJpaRepository;
import com.wesdom.rocio.database.repositories.PendingSMSNotificationRepository;
import com.wesdom.rocio.model.PendingSMSNotification;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PendingSMSNotificationRepositoryImpl implements PendingSMSNotificationRepository {

    @Autowired
    private PendingSMSNotificationJpaRepository pendingSMSNotificationJpaRepository;

    @Override
    public PendingSMSNotification get(Long id) {
        return pendingSMSNotificationJpaRepository.getOne(id);
    }

    @Override
    public PendingSMSNotification create(PendingSMSNotification pendingSMSNotification) {
        return pendingSMSNotificationJpaRepository.saveAndFlush(pendingSMSNotification);
    }

    @Override
    public PendingSMSNotification update(Long id, PendingSMSNotification pendingSMSNotification) {
        return pendingSMSNotificationJpaRepository.saveAndFlush(pendingSMSNotification);
    }

    @Override
    public void delete(Long id) {
        pendingSMSNotificationJpaRepository.deleteById(id);
    }

    @Override
    public Page<PendingSMSNotification> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<PendingSMSNotification> predicateBuilder = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        return pendingSMSNotificationJpaRepository.findAll(predicateBuilder.createPredicate(queryParams)
                ,paginationBuilder.createPagination(queryParams));
    }

    @Override
    public void deleteInList(List<PendingSMSNotification> pendingSMSNotifications) {
        pendingSMSNotificationJpaRepository.deleteAll(pendingSMSNotifications);
    }
}
