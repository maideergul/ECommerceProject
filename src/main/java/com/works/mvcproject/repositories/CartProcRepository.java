package com.works.mvcproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.works.mvcproject.models.CartProc;

public interface CartProcRepository extends JpaRepository<CartProc, Integer> {

	@Query(value = "CALL cart_proc(:p_statu)", nativeQuery = true)
	List<CartProc> getCart( @Param("p_statu") int p_statu );
	
}
