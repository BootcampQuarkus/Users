package com.quarkus.bootcamp.nttdata.infraestructure.entity.card;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CardTypeD {
  protected Long id;
  /**
   * Nombre con el cual nos referimos al tipo de tarjeta.
   * Ejemplo:
   * - Debito
   * - Credito
   */
  protected String name;
  /**
   * Collección de tarjetas pertenecientes a este tipo.
   */
  protected List<CardD> cards;
}
