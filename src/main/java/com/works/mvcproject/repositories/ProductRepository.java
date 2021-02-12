package com.works.mvcproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.works.mvcproject.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where p.title like ?1")
	List<Product> searchData( String data );	
	@Query("select p from Product p where p.cid=?1")	
	List<Product> findByCid(int cid);
	@Query("select p from Product p where p.pstatu=?1")	
	List<Product> countByStatu(int pstatu);
	@Query("select p.price*p.quantity as total from Product p  where p.pstatu=?1")	
	List<Integer> findTotalPriceOfProduct(int statu);		
	@Query("SELECT SUM(p.price*p.quantity) as total FROM Product as p WHERE p.pstatu LIKE ?1")
	Integer findByPstatuContains(int Pstatu);	

}
