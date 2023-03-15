package com.example.socks_warehouse.repository;


import com.example.socks_warehouse.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {


    Optional<Socks> findByColorIgnoreCaseAndCottonPart(String color, int cottonPart);

    Collection<Socks> findByColorIgnoreCaseAndCottonPartBefore(String color, int cottonPart);

    Collection<Socks> findByColorIgnoreCaseAndCottonPartAfter(String color, int cottonPart);



}
