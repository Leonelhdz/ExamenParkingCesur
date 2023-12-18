package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Coche {
    private String matricula;
    private String modelo;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String cliente;
    private Double tarifa;
    private Double coste;

}
