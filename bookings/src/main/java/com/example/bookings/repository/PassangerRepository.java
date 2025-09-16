package com.example.bookings.repository;

import com.example.bookings.entity.PassangerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassangerRepository extends JpaRepository<PassangerEntity,Long> {
}
