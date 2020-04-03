package com.realtion.human.repository;

import com.realtion.human.entity.UserRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserRelationRepository extends CrudRepository<UserRelation, Long> {
    List<UserRelation> findAllByUsersId(@Param("uId") Long userId);
}
