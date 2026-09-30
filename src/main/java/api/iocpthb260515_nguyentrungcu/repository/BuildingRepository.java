package api.iocpthb260515_nguyentrungcu.repository;

import api.iocpthb260515_nguyentrungcu.entity.Building;
import api.iocpthb260515_nguyentrungcu.entity.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findBuildingsByBuildingName(String buildingName);

    @Query("""
        SELECT b
        FROM Building b
        WHERE (:buildingName IS NULL OR LOWER(b.buildingName) LIKE CONCAT("%", CAST(:buildingName as string), "%") )
            AND (:status IS NULL OR :status = b.status)
            """)
    Page<Building> searchBuildings(
            @Param("buildingName") String buildingName,
            @Param("status")StatusEnum status,
            Pageable pageable
            );
}
