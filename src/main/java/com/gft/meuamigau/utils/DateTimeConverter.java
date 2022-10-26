package com.gft.meuamigau.utils;

import static com.gft.meuamigau.enums.constants.ErrorMessages.STRING_TO_DATE_ERROR;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.gft.meuamigau.exceptions.DataIntegrityViolationException;

public class DateTimeConverter {
	
	private DateTimeConverter() {
		throw new IllegalAccessError("Utility Class");
	}

	
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String TIME_PATTERN = "HH:mm:ss";
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * Convert Date to String according pattern definition in DATE_PATTERN 
	 * @param Date date
	 * @return String
	 */
    public static String dateToString(Date date, String pattern) {

    	
    	DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(pattern);
    	
    	LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
    			.atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    	
		return localDateTime.format(datePattern);
    }
    
    /**
	 * Convert String to Date according pattern definition in DATE_PATTERN 
	 * @param String dateString
	 * @return Date
	 */
    public static Date stringtoDate(String dateString, String pattern) {
    	DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(pattern);
    	
    	LocalDateTime localDateTime = LocalDateTime.now();
		try {
			if (pattern.equals(DATE_PATTERN))
				localDateTime = LocalDate.parse(dateString, datePattern).atStartOfDay();
			if (pattern.equals(DATE_TIME_PATTERN))
				localDateTime = LocalDateTime.parse(dateString, datePattern);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(
					String.format(STRING_TO_DATE_ERROR, dateString));
		}
    	
        return Date.from(localDateTime.atZone(
                ZoneId.systemDefault())
            .toInstant());
    }
    
    public static Date dataAtual() {
    	return Date.from(LocalDate
				.now()
				.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
    }
    
    public static Date dataHoraAtual() {
    	return Date.from(LocalDateTime
				.now()
				.atZone(ZoneId.systemDefault())
				.toInstant());
    }
}
