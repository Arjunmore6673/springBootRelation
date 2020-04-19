package com.realtion.human.repository;

import com.realtion.human.entity.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    Feedback findByUserId(Long userId);

}
