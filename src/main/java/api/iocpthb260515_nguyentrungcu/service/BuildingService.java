package api.iocpthb260515_nguyentrungcu.service;

import api.iocpthb260515_nguyentrungcu.dto.request.BuildingCreateDTO;
import api.iocpthb260515_nguyentrungcu.dto.request.BuildingUpdateDTO;
import api.iocpthb260515_nguyentrungcu.dto.response.ApiResponse;
import api.iocpthb260515_nguyentrungcu.dto.response.BuildingResponseDTO;
import api.iocpthb260515_nguyentrungcu.entity.Building;
import api.iocpthb260515_nguyentrungcu.entity.StatusEnum;
import api.iocpthb260515_nguyentrungcu.exception.DuplicateResourceException;
import api.iocpthb260515_nguyentrungcu.repository.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuildingService {
    private BuildingRepository buildingRepository;

    public BuildingResponseDTO createBuilding (BuildingCreateDTO dto) {
        validateDuplicateBuilding(dto.getBuildingName(), null);

        Building building = Building.builder()
                .buildingName(dto.getBuildingName())
                .buildingArea(dto.getBuildingArea())
                .areaUnit(dto.getAreaUnit())
                .startDate(dto.getStartDate())
                .time(dto.getTime())
                .timeUnit(dto.getTimeUnit())
                .design(dto.getDesign())
                .content(dto.getContent())
                .status(dto.getStatus())
                .build();

        Building buildingSaved = buildingRepository.save(building);

        return toResponseDTO(buildingSaved);
    }

    public BuildingResponseDTO updateBuilding (Long id, BuildingUpdateDTO dto) {
        validateDuplicateBuilding(dto.getBuildingName(), id);

        Building building = new Building();

        building.setBuildingName(dto.getBuildingName());
        building.setBuildingArea(dto.getBuildingArea());
        building.setAreaUnit(dto.getAreaUnit());
        building.setStartDate(dto.getStartDate());
        building.setTime(dto.getTime());
        building.setTimeUnit(dto.getTimeUnit());
        building.setDesign(dto.getDesign());
        building.setContent(dto.getContent());
        building.setStatus(dto.getStatus());

        Building buildingSaved = buildingRepository.save(building);

        return toResponseDTO(buildingSaved);
    }

    public Page<BuildingResponseDTO> getBuildings (
            String buildingName,
            StatusEnum status,
            Integer page,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Building> buildings = buildingRepository.searchBuildings(buildingName, status, pageable);

        return buildings.map(this::toResponseDTO);
    }

    public void validateDuplicateBuilding(String buildingName, Long currentId) {
        buildingRepository.findBuildingsByBuildingName(buildingName).ifPresent(building -> {
            if(!building.getId().equals(currentId)) {
                throw new DuplicateResourceException("Building Name already exist");
            }
        });
    }

    public BuildingResponseDTO toResponseDTO(Building building) {
        return BuildingResponseDTO.builder()
                .id(building.getId())
                .buildingName(building.getBuildingName())
                .buildingArea(building.getBuildingArea())
                .areaUnit(building.getAreaUnit())
                .startDate(building.getStartDate())
                .time(building.getTime())
                .timeUnit(building.getTimeUnit())
                .design(building.getDesign())
                .content(building.getContent())
                .status(building.getStatus())
                .build();
    }
}
