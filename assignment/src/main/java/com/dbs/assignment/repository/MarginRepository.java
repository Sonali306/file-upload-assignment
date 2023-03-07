package com.dbs.assignment.repository;

import com.dbs.assignment.model.Margin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarginRepository extends CrudRepository<Margin, Integer> {

    @Query("Select m from Margin m where (m.instruction=:instruction or m.instruction ='*') and (m.baseCcy=:baseCcy or m.baseCcy='*') and (m.termCcy=:termCcy or m.termCcy='*') and (m.tradeTier=:tradeTier or m.tradeTier='0') and ((m.fromAmount<=:amount and m.toAmt >= :amount) or (m.fromAmount='0' and m.toAmt = '0'))")
    List<Margin> findMarginByPayLoad(@Param("instruction") String instruction, @Param("baseCcy") String baseCcy, @Param("termCcy") String termCcy, @Param("tradeTier") String tradeTier, @Param("amount") String amount);
}
