package com.storyshell.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticData extends ElasticsearchRepository<Object, String> {
    
}
