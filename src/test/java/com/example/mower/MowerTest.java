package com.example.mower;

import org.junit.jupiter.api.Test;

import static com.example.mower.Orientation.N;
import static org.assertj.core.api.Assertions.assertThat;

public class MowerTest {
  @Test
  public void whenCreateMowerWithInitialPosition_thenPositionisAsExpected() {
    Mower mower = new Mower(1, 2, N);
    Position expectedPosition = new Position(1, 2, N);
    assertThat(mower.getPosition()).isEqualTo(expectedPosition);
  }
}
