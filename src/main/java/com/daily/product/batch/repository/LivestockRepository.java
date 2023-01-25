package com.daily.product.batch.repository;

import com.daily.product.batch.domain.Livestock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivestockRepository extends JpaRepository<Livestock, Long> {
}
