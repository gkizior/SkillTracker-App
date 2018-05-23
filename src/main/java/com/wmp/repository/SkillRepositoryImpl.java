package com.wmp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import com.wmp.model.Skill;
 
@Repository
public class SkillRepositoryImpl implements SkillRepository {
 
	@Autowired
	private SolrTemplate solrTemplate;
 
    @Override
    public List<Skill> search(String searchTerm) {
        String[] words = searchTerm.split(" ");
 
        Criteria conditions = createSearchConditions(words);
        SimpleQuery search = new SimpleQuery(conditions);
 
        Page results = solrTemplate.queryForPage(search, Skill.class);
        return results.getContent();
    }
 
    private Criteria createSearchConditions(String[] words) {
        Criteria conditions = null;
 
        for (String word: words) {
            if (conditions == null) {
                conditions = new Criteria("skills").contains(word);
            }
            else {
                conditions = conditions.or(new Criteria("skills").contains(word));
            }
        }
 
        return conditions;
    }

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Skill> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Skill> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Skill> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Skill> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Skill> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Skill> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Skill entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Skill> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
 
    //Other methods are omitted
}