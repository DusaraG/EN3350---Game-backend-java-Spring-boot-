package com.example.EnergycorpBackend.repository;

import com.example.EnergycorpBackend.dao.PlayerAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedAnswers extends CrudRepository<PlayerAttempt,Integer> {

}
