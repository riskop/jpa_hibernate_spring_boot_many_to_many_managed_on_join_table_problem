# jpa_hibernate_spring_boot_many_to_many_managed_on_join_table_problem
jpa_hibernate_spring_boot_many_to_many_managed_on_join_table_problem

I have a many to many relationship between POSTs and TAGs. Each post can have many tags, each tag can have many posts.

I would like to manage the relationship with an entity mapped to the join table. So I have 3 entity classes: Post, Tag, PostTag. PostTag has @ManyToOne annotated Post and Tag members.

When I need the Posts with a given Tag, I would like to use this query:

    @Query(value = "select pt from PostTag pt join fetch pt.post where pt.id.tagId = :tagId")
    Set<PostTag> findByTagIdAndFetchPosts(@Param("tagId") Long tagId);

The problem is that Hibernate creates this select:

    2020-08-25 10:21:57.486 DEBUG 16791 --- [           main] org.hibernate.SQL                        : 
        select
            posttag0_.post_id as post_id1_1_0_,
            posttag0_.tag_tag_id as tag_tag_2_1_0_ 
        from
            post_tag posttag0_ 
        where
            posttag0_.post_id=? 
            and posttag0_.tag_tag_id=?

Which causes the following error:

    2020-08-25 10:21:57.487  WARN 16791 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 42122, SQLState: 42S22
    2020-08-25 10:21:57.487 ERROR 16791 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : Column "POSTTAG0_.TAG_TAG_ID" not found; SQL statement:
    select posttag0_.post_id as post_id1_1_0_, posttag0_.tag_tag_id as tag_tag_2_1_0_ from post_tag posttag0_ where posttag0_.post_id=? and posttag0_.tag_tag_id=? [42122-200]

What's wrong with it?

