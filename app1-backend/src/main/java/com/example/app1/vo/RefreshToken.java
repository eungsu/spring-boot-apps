package com.example.app1.vo;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("RefreshToken")
public class RefreshToken {

  private int no;
  private int userNo;
  private String token;
  private LocalDateTime expires;

  public boolean isExpired() {
    return expires.isBefore(LocalDateTime.now());
  }

  public boolean isExpirationTimeWithinOneHour() {
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(expires, now).abs();
    Duration oneHour = Duration.ofHours(1);

    return duration.compareTo(oneHour) < 0;
  }

}
