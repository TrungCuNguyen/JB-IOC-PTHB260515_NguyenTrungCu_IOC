package api.iocpthb260515_nguyentrungcu.dto.response;

import api.iocpthb260515_nguyentrungcu.entity.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
public class BuildingResponseDTO {
    private Long id;
    private String buildingName;

    private BigDecimal buildingArea;

    private String areaUnit;

    private LocalDate startDate;

    private Integer time;

    private String timeUnit;

    private String design;

    private String content;

    private StatusEnum status;
}
