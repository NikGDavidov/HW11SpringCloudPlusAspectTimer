package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.aspect.HWAspect;


@Configuration
public class AspectAutoConfiguration {
 @Bean
 HWAspect hwAspect() {
  return new HWAspect();
 }
}
