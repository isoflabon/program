package com.example.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.example.entity.TestData;
 
@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long>{
}
