package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.PendingSMSNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingSMSNotificationJpaRepository extends JpaRepository<PendingSMSNotification,Long>, JpaSpecificationExecutor<PendingSMSNotification> {
}
