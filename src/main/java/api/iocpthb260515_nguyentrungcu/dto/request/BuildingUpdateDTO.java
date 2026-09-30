package api.iocpthb260515_nguyentrungcu.dto.request;

import api.iocpthb260515_nguyentrungcu.entity.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingUpdateDTO {
    @NotBlank
    @Size(min = 1, max = 100, message = "Tên tòa nhà từ 1-100 ký tự")
    private String buildingName;

    @NotNull
    private BigDecimal buildingArea;

    @NotNull
    @Size(min = 1, max = 10)
    private String areaUnit;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Integer time;

    @NotBlank
    @Size(min = 1, max = 10)
    private String timeUnit;

    @NotBlank
    @Size(min = 1, max = 255)
    private String design;

    @NotBlank
    @Size(min = 1, max = 255)
    private String content;

    @NotNull
    private StatusEnum status;
}
