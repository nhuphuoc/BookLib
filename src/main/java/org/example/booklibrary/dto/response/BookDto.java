package org.example.booklibrary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.booklibrary.entity.Author;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

  private String title;
  private String authorName;
  private String genre;
  @JsonFormat(pattern = "yyyy/MM/dd")
  private Date publicationDate;
  private int quantity;
}
