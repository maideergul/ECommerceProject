package com.works.mvcproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.works.mvcproject.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("select c from Category c where c.categoryname like ?1")
	List<Category> searchData( String data );
	
}
