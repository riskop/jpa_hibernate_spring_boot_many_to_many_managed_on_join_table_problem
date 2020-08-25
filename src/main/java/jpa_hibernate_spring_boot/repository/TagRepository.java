package jpa_hibernate_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_hibernate_spring_boot.entity.Tag;;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
