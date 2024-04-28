package com.example.bookmanager.domain.converter;

import com.example.bookmanager.repository.dto.BookStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // autoApply가 true일 경우, Entity에 @Convert를 달지 않아도 자동 적용됨
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(BookStatus attribute) {
    return attribute.getCode();
  }

  @Override
  public BookStatus convertToEntityAttribute(Integer dbData) {
    return dbData != null ? new BookStatus(dbData) : null;
  }
}
