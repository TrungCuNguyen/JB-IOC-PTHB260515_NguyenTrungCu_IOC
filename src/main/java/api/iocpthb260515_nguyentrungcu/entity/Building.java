package api.iocpthb260515_nguyentrungcu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String buildingName;

    @Column(nullable = false)
    private BigDecimal buildingArea;

    @Column(length = 10, nullable = false)
    private String areaUnit;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Integer time;

    @Column(length = 10,nullable = false)
    private String timeUnit;

    @Column(length = 255,nullable = false)
    private String design;

    @Column(length = 255,nullable = false)
    private String content;

    @Column(nullable = false)
    private StatusEnum status;
}
