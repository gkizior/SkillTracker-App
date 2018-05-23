package com.wmp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wmp.model.Skill;

@Repository
public interface CustomSkillRepository {
 
    public List<Skill> search(String searchTerm);
 
    //Other methods are omitted.
}