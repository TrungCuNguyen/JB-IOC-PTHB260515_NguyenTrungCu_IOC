package api.iocpthb260515_nguyentrungcu.controller;

import api.iocpthb260515_nguyentrungcu.dto.request.BuildingCreateDTO;
import api.iocpthb260515_nguyentrungcu.dto.request.BuildingUpdateDTO;
import api.iocpthb260515_nguyentrungcu.dto.response.ApiResponse;
import api.iocpthb260515_nguyentrungcu.dto.response.BuildingResponseDTO;
import api.iocpthb260515_nguyentrungcu.entity.StatusEnum;
import api.iocpthb260515_nguyentrungcu.service.BuildingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/buildings")
public class BuildingController {
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BuildingResponseDTO>>> getAllBuildings(
            @RequestParam(required = false) String buildingName,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {

        StatusEnum statusEnum = status != null ? StatusEnum.fromValue(status) : null;
        Page<BuildingResponseDTO> buildingResponse = buildingService.getBuildings(buildingName, statusEnum,
                page, size);
        ApiResponse<Page<BuildingResponseDTO>> res = ApiResponse.<Page<BuildingResponseDTO>>builder()
                .success(true)
                .httpStatus(HttpStatus.OK.value())
                .data(buildingResponse)
                .build();

        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BuildingResponseDTO>> createBuilding(@Valid @RequestBody BuildingCreateDTO buildingCreateDTO) {
        BuildingResponseDTO buildingResponse = buildingService.createBuilding(buildingCreateDTO);
        ApiResponse<BuildingResponseDTO> res = ApiResponse.<BuildingResponseDTO>builder()
                .success(true)
                .httpStatus(HttpStatus.CREATED.value())
                .data(buildingResponse)
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BuildingResponseDTO>> updateBuilding(@Valid @RequestBody BuildingUpdateDTO buildingUpdateDTO, @PathVariable Long id) {
        BuildingResponseDTO buildingResponse = buildingService.updateBuilding(id, buildingUpdateDTO);
        ApiResponse<BuildingResponseDTO> res = ApiResponse.<BuildingResponseDTO>builder()
                .success(true)
                .httpStatus(HttpStatus.OK.value())
                .data(buildingResponse)
                .build();
        return  ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
