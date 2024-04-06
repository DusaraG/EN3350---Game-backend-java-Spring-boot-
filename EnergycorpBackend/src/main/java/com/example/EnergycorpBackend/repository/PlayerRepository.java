package com.example.EnergycorpBackend.repository;

import com.example.EnergycorpBackend.dao.PlayerDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends CrudRepository<PlayerDao,Integer> {

}
