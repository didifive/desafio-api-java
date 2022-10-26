package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gft.meuamigau.exceptions.DataIntegrityViolationException;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Utils -> DateTimeConverter")
class DateTimeConverterTest {
	
	private Date date;
	private Date dateTime;
	private String dateString;
	private String dateTimeString;
	
	private static final String INVALID_STRING_DATE = "99750/2479";
	
	@BeforeEach
	private void setup() {
		date = Date.from(
				LocalDate.of(2000, 10, 01) 
				.atStartOfDay(ZoneId.systemDefault())
				.toInstant());
		dateTime = Date.from(
				LocalDate.of(2000, 10, 01)
				.atTime(15, 50, 21)
				.atZone(ZoneId.systemDefault())
				.toInstant());   
		dateString = "01/10/2000";
		dateTimeString = "01/10/2000 15:50:21";
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<DateTimeConverter> constructor = DateTimeConverter.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test
	@DisplayName("1. Deve converter Date para String")
	void shouldConvertDateToString() throws Exception {
		//then
		assertEquals(dateString, DateTimeConverter.dateToString(date, DATE_PATTERN));
	}
	
	@Test
	@DisplayName("2. Deve converter String para Date")
	void shouldConvertStringToDate() throws Exception {
		//then
		assertEquals(date, DateTimeConverter.stringtoDate(dateString, DATE_PATTERN));
	}
	
	@Test
	@DisplayName("2. Deve converter String para DateTime")
	void shouldConvertStringToDateTime() throws Exception {
		//then
		assertEquals(dateTime, DateTimeConverter.stringtoDate(dateTimeString, DATE_TIME_PATTERN));
	}
	
	@Test
	@DisplayName("2.3 Deve lançar DataIntegrityViolationException")
	void shouldThrowDataIntegrityViolationExceptions() throws Exception {
		//then
		assertThrows(DataIntegrityViolationException.class,
				() -> DateTimeConverter.stringtoDate(INVALID_STRING_DATE, DATE_PATTERN));
	}
}
