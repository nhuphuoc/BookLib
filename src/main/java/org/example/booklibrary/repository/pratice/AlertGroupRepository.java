package org.example.booklibrary.repository.pratice;
import org.example.booklibrary.entity.practice.AlertDetails;
import org.example.booklibrary.entity.practice.AlertGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlertGroupRepository extends JpaRepository<AlertGroup, String> { }
