package jpa_hibernate_spring_boot.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jpa_hibernate_spring_boot.entity.PostTag;
import jpa_hibernate_spring_boot.entity.PostTagId;;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {

    // fetching posts with join 
    @Query(value = "select pt from PostTag pt join fetch pt.post where pt.id.tagId = :tagId")
    Set<PostTag> findByTagIdAndFetchPosts(@Param("tagId") Long tagId);

}
