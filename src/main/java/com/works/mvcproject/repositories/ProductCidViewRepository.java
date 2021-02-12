package com.works.mvcproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.works.mvcproject.models.ProductCidView;

public interface ProductCidViewRepository extends JpaRepository<ProductCidView, Integer> {
	
	@Query("select p from ProductCidView p where p.title like ?1")
	List<ProductCidView> searchData( String data );

}
