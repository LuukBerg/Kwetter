package fontys.com.kwetterdoa.Repository;

import feign.Param;
import fontys.com.kwetterdoa.Model.Kweet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KweetRepository extends JpaRepository<Kweet, Long> {
    @Query("SELECT k from Kweet k inner join k.owner p where p.id in (SELECT p.id FROM Profile p join p.followers f where f.id = :id) or p.id = :id order by k.date desc")
    List<Kweet> getTimeline(@Param("id") long id);

    @Query("SELECT k from Kweet k WHERE k.owner.id = :id")
    List<Kweet> findbyProfileId(@Param("id") long id);
}
