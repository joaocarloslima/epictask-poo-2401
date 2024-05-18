package br.senac.sp.epictask;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    // @Pattern(regexp = "DEBITO|CREDITO|PIX")
    // private String formaDePagamento;

    // @PastOrPresent
    // private LocalDate dataPagamento;

    
    private String description;

    @Min(0)
    private Integer score;

    private Integer status;
    
}
