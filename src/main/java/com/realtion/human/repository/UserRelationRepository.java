package com.realtion.human.repository;

import com.realtion.human.entity.UserRelation;
import com.realtion.human.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRelationRepository extends CrudRepository<UserRelation, Long> {
    List<UserRelation> findAllByUsersId(@Param("uId") Long userId);
    List<UserRelation> findAllByUsers2Id(@Param("uId") Long userId);

    UserRelation findAllByUsersAndUsers2(@Param("uId") Users userId, @Param("uId2") Users userId2);

}
