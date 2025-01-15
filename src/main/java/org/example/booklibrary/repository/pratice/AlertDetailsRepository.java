package org.example.booklibrary.repository.pratice;

import org.example.booklibrary.entity.practice.AlertDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertDetailsRepository extends JpaRepository<AlertDetails, String> { }