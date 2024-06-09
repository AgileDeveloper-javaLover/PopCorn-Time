package com.Blackpool.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blackpool.Models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}
