package com.pavlov.testtask_urlhashtables.repository;

import com.pavlov.testtask_urlhashtables.entity.YesterdayURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YesterdayURLRepository extends JpaRepository<YesterdayURL, String> {
}
