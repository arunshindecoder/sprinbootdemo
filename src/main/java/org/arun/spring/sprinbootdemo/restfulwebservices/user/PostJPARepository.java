package org.arun.spring.sprinbootdemo.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false) //Arun For individual entity not to expose CRUD operations.
public interface PostJPARepository extends JpaRepository<Post,Integer> {

}
