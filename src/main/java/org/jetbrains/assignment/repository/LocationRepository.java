package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.entity.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Actions, Long> {
}
