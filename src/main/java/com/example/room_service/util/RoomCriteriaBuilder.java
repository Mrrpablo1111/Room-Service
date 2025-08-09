package com.example.room_service.util;

import com.example.room_service.dto.RoomFilterDTO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Objects;

public class RoomCriteriaBuilder {
    public static Query build(RoomFilterDTO filter){
        Criteria criteria = new Criteria();
        if(Objects.nonNull(filter.getName())){
            criteria.and("name").is(filter.getName());
        }
        if(Objects.nonNull(filter.getFloor())){
            criteria.and("attributes.floor").is(filter.getFloor());
        }
        Query query = new Query(criteria);
        return query;
    }
}
