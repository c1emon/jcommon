package icu.clemon.jcommon.types;

import lombok.Data;

@Data
public class EnumeratorProxy implements Enumerator {
  private Integer id;
  private String description;
}
