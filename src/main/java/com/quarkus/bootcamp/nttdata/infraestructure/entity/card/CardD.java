package com.quarkus.bootcamp.nttdata.infraestructure.entity.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardD {
  protected Long id;
  /**
   * Numero de la tarjeta de 16 digitos en grupos de 4 caracteres
   * separados por "-".
   * El numero de la tarjeta debe cumplir el algoritmo de
   * Luhn.
   */
  protected String serial;
  /**
   * Contraseña de la tarjeta.
   */
  protected String pin;
  /**
   * Mes de vencimiento de la tarjta.
   */
  protected Integer month;
  /**
   * Año de vencimiento de la tarjeta.
   */
  protected Integer year;
  /**
   * Cvv
   */
  protected Integer cvv;
  /**
   * Identificador del cliente dueño de la tarjeta.
   */
  protected Long customerId;
  /**
   * Identificador del producto principal asociado a la tarjeta.
   */
  protected Long productId;
  /**
   * Tipo de tarjeta.
   */
  protected CardTypeD cardType;
  /**
   * Identificador del tipo de tarjeta.
   */
  protected Long cardTypeId;

}
