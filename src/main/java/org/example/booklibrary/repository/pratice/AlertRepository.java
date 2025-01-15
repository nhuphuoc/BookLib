package org.example.booklibrary.repository.pratice;

import org.example.booklibrary.entity.practice.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> { }
