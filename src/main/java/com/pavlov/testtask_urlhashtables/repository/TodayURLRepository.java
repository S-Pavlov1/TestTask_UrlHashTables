package com.pavlov.testtask_urlhashtables.repository;

import com.pavlov.testtask_urlhashtables.entity.TodayURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayURLRepository extends JpaRepository<TodayURL, String> {
}
