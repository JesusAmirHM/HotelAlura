package com.alura.aluraHotel.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertirFecha {

	public static LocalDate convertirDateALocalDate(Date fechaAConvertir) {
        return fechaAConvertir.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
